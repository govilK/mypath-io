package com.gps.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class ConnectionClient {
	
	
	public String getJsonData(String urlstr) throws Exception
	{

		HttpURLConnection httpURLConnection;
		StringBuilder responseStr = new StringBuilder();
		try
		{
			URL url = new URL(urlstr);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
//
//			
//			URL url = new URL(urlstr);
//		      HttpURLConnection httpconn = (HttpURLConnection)url.openConnection();
//		      if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK)
//		      {
//		          BufferedReader input = new BufferedReader(new InputStreamReader(httpconn.getInputStream()),8192);
//		          String strLine = null;
//		          while ((strLine = input.readLine()) != null)
//		          {
//		              responseStr.append(strLine);
//		          }
//		          input.close();
//		      }
//		      String jsonOutput = responseStr.toString();

			/*if (httpURLConnection instanceof HttpsURLConnection)
			{
			SSLContext sslContext = SSLContext.getInstance("SSL");
				//we are using an empty trust manager, because NetScaler currently presents
				//a test certificate not issued by any signing authority, so we need to bypass
				//the credentials check
				sslContext.init(null, new TrustManager[]{new EmptyTrustManager()}, null);
				SocketFactor sslSocketFactory = sslContext.getSocketFactory();

				HttpsURLConnection secured = (HttpsURLConnection) httpURLConnection;
				secured.setSSLSocketFactory(sslSocketFactory);
				//secured.setHostnameVerifier(new EmptyHostnameVerifier());
			}*/
			InputStream input = httpURLConnection.getInputStream();
			String contentEncoding = httpURLConnection.getContentEncoding();
			//	get correct input stream for compressed data:
			if (contentEncoding != null)
			{
				if(contentEncoding.equalsIgnoreCase("gzip"))
					input = new GZIPInputStream(input); //reads 2 bytes to determine GZIP stream!
				else if(contentEncoding.equalsIgnoreCase("deflate"))
					input = new InflaterInputStream(input);
			}

			int numOfTotalBytesRead;
			byte [] buffer = new byte[1024];
			while ((numOfTotalBytesRead = input.read(buffer, 0, buffer.length)) != -1)
			{
				responseStr.append(new String(buffer, 0, numOfTotalBytesRead));
			}

			httpURLConnection.disconnect();
			input.close();
		}
		catch (MalformedURLException mue)
		{
			System.err.println("Invalid URL");
		}
		catch (IOException ioe)
		{
			System.err.println("I/O Error - " + ioe);
		}
		catch(Exception e)
		{
			System.err.println("Error - " + e);
		}
		return responseStr.toString();
	
	}

	public String postData(String urlstr)
	{

			HttpURLConnection httpURLConnection;
			StringBuilder responseStr = new StringBuilder();
			try
			{
				URL url = new URL(urlstr);
				httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestMethod("POST");

				/*if (httpURLConnection instanceof HttpsURLConnection)
				{
				SSLContext sslContext = SSLContext.getInstance("SSL");
					//we are using an empty trust manager, because NetScaler currently presents
					//a test certificate not issued by any signing authority, so we need to bypass
					//the credentials check
					sslContext.init(null, new TrustManager[]{new EmptyTrustManager()}, null);
					SocketFactor sslSocketFactory = sslContext.getSocketFactory();

					HttpsURLConnection secured = (HttpsURLConnection) httpURLConnection;
					secured.setSSLSocketFactory(sslSocketFactory);
					//secured.setHostnameVerifier(new EmptyHostnameVerifier());
				}*/
				InputStream input = httpURLConnection.getInputStream();
//				String contentEncoding = httpURLConnection.getContentEncoding();
//				//	get correct input stream for compressed data:
//				if (contentEncoding != null)
//				{
//					if(contentEncoding.equalsIgnoreCase("gzip"))
//						input = new GZIPInputStream(input); //reads 2 bytes to determine GZIP stream!
//					else if(contentEncoding.equalsIgnoreCase("deflate"))
//						input = new InflaterInputStream(input);
//				}
//
//				int numOfTotalBytesRead;
//				byte [] buffer = new byte[1024];
//				while ((numOfTotalBytesRead = input.read(buffer, 0, buffer.length)) != -1)
//				{
//					responseStr.append(new String(buffer, 0, numOfTotalBytesRead));
//				}
			}
			catch (MalformedURLException mue)
			{
				System.err.println("Invalid URL");
			}
			catch (IOException ioe)
			{
				System.err.println("I/O Error - " + ioe);
			}
			catch(Exception e)
			{
				System.err.println("Error - " + e);
			}
			return responseStr.toString();
		
	}
}
