package com.consultancy.company.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.consultancy.company.DateLabelFormatter1;
import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.HoursDB;
import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.Hours;
import com.consultancy.company.model.Invoice;
import com.consultancy.company.model.People;
import com.consultancy.company.model.Project;

public class GenerateInvoice {

	
	public boolean invoicereport = false;
	
	boolean isWithinRange(Date testDate,Date startDate,Date endDate) {
		   return !(testDate.before(startDate) || testDate.after(endDate));
		}
	public String generatInvoice(String startdate, String enddate,
			String projectnumber,String projectname) throws ParseException {
		String htmlcontent = "";

		try{
		
	     SimpleDateFormat dateFormatter = new SimpleDateFormat(DateLabelFormatter1.datePattern);

	     System.out.println("Testing");
		Date startdated = dateFormatter.parse(startdate);
		Date enddated = dateFormatter.parse(enddate);
		
		ProjectDB projectDB = new ProjectDB();
		Project project = projectDB.get(Integer.parseInt(projectnumber));
		
		ClientDB clientDB = new ClientDB();
		Client client = clientDB.get(project.getClient());
		
		System.out.println(projectnumber);
		HoursDB hoursDB = new HoursDB();
		List<Hours> hourslist = hoursDB.getHoursbypnumber(Integer
				.parseInt(projectnumber));
		
		System.out.println("Hours list"+hourslist.size());
		double totaldue = 0;
		Map<Integer, Invoice> invoicemap = new HashMap<>();

		for (Hours hours : hourslist) {

			Date hoursd=null;
			try {
				hoursd = dateFormatter.parse(hours.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(!isWithinRange(hoursd, startdated, enddated)){

				continue;
			}

			PeopleDB peopleDB = new PeopleDB();
			System.out.println("HOURS"+hours.getEmpid());
			People people = peopleDB.get(hours.getEmpid());
			Invoice invoice;
			if (invoicemap.containsKey(hours.getEmpid())) {

				invoice = invoicemap.get(hours.getEmpid());

			}

			else {
				invoice = new Invoice();

			}

			invoice.setTotalhours(invoice.getTotalhours() + hours.getHours());
			invoice.setPersonaname(people.getName());
			invoice.setBillrate(people.getBillrate());
			invoicemap.put(hours.getEmpid(), invoice);
			System.out.println("here");
			totaldue = totaldue+(hours.getHours()*people.getBillrate());
		}
		
		

		File in = new File("src/META-INF/template.html");
		try {
			
			
			
			Document doc = Jsoup.parse(in, null);
			 doc.select("#logoimage").get(0).attr("src", new File("logo.png").getAbsolutePath());
			 doc.select("span.invoicenumber").html(projectnumber);
			 doc.select("span.projectname").html(projectname);
			 Date date = new Date();
			 String modifiedDate= new SimpleDateFormat("M/dd/yyyy").format(date);
			 doc.select("span.invoicedate").html(modifiedDate);
			 doc.select("span.paymentterms").html(client.getInvoicefreq());
			 doc.select("span.billingfrequency").html(client.getBillingterms());
			 doc.select("span.totalamountdue").html(""+totaldue);
			 doc.select("span.clientid").html(""+client.getCnumber());
	      	 System.out.println("text");

			 doc.select(".tocompanyname").html(client.getName());
			 doc.select(".toaddress").html(client.getAddressline1()+" "+client.getAddressline2());
			 doc.select(".tocity").html(""+client.getCity()+", "+client.getState()+" ,"+client.getZip());

			 
	      	 Element info = doc.select("#detailtemplate").get(0);

			 Element invoicetable = doc.select("#invoicetable").get(0);
			 
			 for (Map.Entry<Integer, Invoice> entry : invoicemap.entrySet())
			 {
				 System.out.println("ENTRY SI"+invoicemap.size());
				 Invoice invoice = entry.getValue();
				 Element tempinfo = info;
				 
				 Calendar c = Calendar.getInstance();
				 c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

				 Calendar c1 = Calendar.getInstance();
				 c1.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				 
				 SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");

				 tempinfo.select("span.detailsdate").html(startdate+ " - "+enddate);
				 
				 tempinfo.select("span.detailsamount").html(""+(invoice.getBillrate()*invoice.getTotalhours()));
				 tempinfo.select("span.detailshours").html(""+(invoice.getTotalhours()));
				 tempinfo.select("span.detailsrate").html(""+(invoice.getBillrate()));
				 tempinfo.select("span.detailsdescription").html(""+(invoice.getPersonaname()));
				 invoicetable.append("<tr>"+tempinfo.html()+"</tr>");
				 
				 
//				 
//				 System.out.println(tempinfo.html());
				 
			     //System.out.println(entry.getKey() + "/" + entry.getValue());
			     
			 }
			 
			 
			 
			invoicetable.select("tr").last().remove();
			
			if(invoicereport){
				
				return invoicetable.outerHtml();
			 }

			 htmlcontent = doc.outerHtml();
				System.out.println(htmlcontent);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return htmlcontent;
	}
	
	
}
