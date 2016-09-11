package com.dollarcompare;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.jws.soap.SOAPBinding;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Compare {

	private static Message  msg;
	private static Properties props;
	private static Session session;

	 private static void getRemitlyRates(){
		Response response;
		Connection connection;
		Document doc;
		try {
			connection = Jsoup.connect("https://www.remitly.com/us/en/india");
			response = connection.execute();
			doc = response.parse();
		
			
			Elements content = doc.getElementsByClass("cms-widget-value-prop-icon");
			String everydayRate = doc.getElementById("everyday-rate").childNode(1).childNode(0).toString().substring(2);
			String expressRate = content.get(1).childNode(0).toString().substring(1);
			//System.out.println("Body:"+doc.html());
			//System.out.println("Data:"+doc.text());
			msg.setText("***************Remitly Rates*******************");
			msg.setText("Express Rate = "+expressRate);
			msg.setText("Everyday Rate = "+everydayRate);
			msg.setText("Transaction charges till $1000 = $3.99");
			msg.setText("Transaction charges above 1000 = $0");
			
			System.out.println("***************Remitly Rates*******************");
			System.out.println("Express Rate = "+expressRate);
			System.out.println("Everyday Rate = "+everydayRate);
			System.out.println("Transaction charges till $1000 = $3.99");
			System.out.println("Transaction charges above 1000 = $0");
			//Elements content = doc.getElementsByClass("cms-widget-value-prop-icon");			
			//content.add(doc.getElementsByClass("cms-widget-value-prop-icon").first());
			/*for(Element element:content){
				System.out.println("Element  = "+element);
				//System.out.println("Element = "+doc.getElementsByClass("exchrate").first()+"Element 2 = "+doc.getElementsByClass("cms-widget-value-prop-icon").first());
			}
			*/
			/*
			for(Element element:content2){
				System.out.println("Element2  = "+element);
				//System.out.println("Element = "+doc.getElementsByClass("exchrate").first()+"Element 2 = "+doc.getElementsByClass("cms-widget-value-prop-icon").first());
			}*/		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getXoom(){
		
		Response response;
		Connection connection;
		Document doc;

			try {
				connection = Jsoup.connect("https://www.xoom.com/india/send-money");
     			connection.userAgent("Mozilla/5.0 (platform; rv:geckoversion) Gecko/geckotrail appname/appversion");
     			response = connection.execute();
				doc = response.parse();
				//System.out.println("Body:"+doc.html());
				
				//System.out.println("Data:"+doc.text());
				
				
				String content = doc.getElementsByClass("fx-rate").get(1).childNode(0).toString().substring(8, 13);
				//String everydayRate = doc.getElementById("everyday-rate").childNode(1).childNode(0).toString().substring(2);
				//String expressRate = content.get(1).childNode(0).toString().substring(1);
				//System.out.println("Body:"+doc.html());
				//System.out.println("Data:"+doc.text());
				System.out.println("***************Xoom Rates*******************");
				System.out.println("Xoom Rate = "+content);
				System.out.println("Transaction charges till 500 = $2.99");
				System.out.println("Transaction charges from 500 - 1000 = $4.99");
				System.out.println("Transaction charges above 1000 = $0.00");
				//System.out.println("Everyday Rate = "+everydayRate);

				//Elements content = doc.getElementsByClass("cms-widget-value-prop-icon");			
				//content.add(doc.getElementsByClass("cms-widget-value-prop-icon").first());
				/*for(Element element:content){
					System.out.println("Element  = "+element);
					//System.out.println("Element = "+doc.getElementsByClass("exchrate").first()+"Element 2 = "+doc.getElementsByClass("cms-widget-value-prop-icon").first());
				}
				
				/*
				for(Element element:content2){
					System.out.println("Element2  = "+element);
					//System.out.println("Element = "+doc.getElementsByClass("exchrate").first()+"Element 2 = "+doc.getElementsByClass("cms-widget-value-prop-icon").first());
				}*/		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
	}
	//
	 
	 private static void getRia(){
		Response response;
		Connection connection;
		Document doc;
		try {
			connection = Jsoup.connect("https://www.riamoneytransfer.com/price-calculator");
 			connection.userAgent("Mozilla/5.0 (platform; rv:geckoversion) Gecko/geckotrail appname/appversion");
			response = connection.execute();
			doc = response.parse();
			int index = doc.html().toString().indexOf("ExchangeRate");
			
			//System.out.println("Body:"+doc.html());
			//System.out.println("Ria ID = "+doc.getElementById("input-amt").toString());
			System.out.println("***********Ria Money Transfer***************");
			System.out.println("Rate = "+doc.html().substring(index+14, index+19));
			
/*			
			Elements content = doc.getElementsByClass("cms-widget-value-prop-icon");
			String everydayRate = doc.getElementById("everyday-rate").childNode(1).childNode(0).toString().substring(2);
			String expressRate = content.get(1).childNode(0).toString().substring(1);
			//System.out.println("Data:"+doc.text());
			System.out.println("***************Remitly Rates*******************");
			System.out.println("Express Rate = "+expressRate);
			System.out.println("Everyday Rate = "+everydayRate);
			System.out.println("Transaction charges till $1000 = $3.99");
			System.out.println("Transaction charges above 1000 = $0");
			//Elements content = doc.getElementsByClass("cms-widget-value-prop-icon");			
			//content.add(doc.getElementsByClass("cms-widget-value-prop-icon").first());
			/*for(Element element:content){
				System.out.println("Element  = "+element);
				//System.out.println("Element = "+doc.getElementsByClass("exchrate").first()+"Element 2 = "+doc.getElementsByClass("cms-widget-value-prop-icon").first());
			}
			*/
			/*
			for(Element element:content2){
				System.out.println("Element2  = "+element);
				//System.out.println("Element = "+doc.getElementsByClass("exchrate").first()+"Element 2 = "+doc.getElementsByClass("cms-widget-value-prop-icon").first());
			}*/		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	 private static void sendEmail(){
		 try {
			  msg = new MimeMessage(session);
			  msg.setFrom(new InternetAddress("shridhar.yadav88@gmail.com", "Shrridhar Yadav"));
			  msg.addRecipient(Message.RecipientType.TO,
			                   new InternetAddress("shridhar.yadav88@gmail.com", "Mr. User"));
			  msg.setSubject("Your Example.com account has been activated");
			 Transport transport = session.getTransport("smtp");
			 transport.connect("smtp.gmail.com", "shridhar.yadav88@gmail.com", "pwd");
			 transport.send(msg);
			 transport.close();
			} catch (AddressException e) {
				e.printStackTrace();
			  // ...
			} catch (MessagingException e) {
				e.printStackTrace();
				// ...
			} catch (UnsupportedEncodingException e) {
			  // ...
				e.printStackTrace();
			}
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		String userName = "shridhar.yadav88@gmail.com";
		String password = "pwd";
		session = Session.getInstance(props);
		msg = new MimeMessage(session);

		getRemitlyRates();
		getXoom();
		getRia();
		sendEmail();
	}

}
