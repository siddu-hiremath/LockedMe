package com.lockedme.project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Launch 
{
	private static PrintWriter rpw;
	private static BufferedReader br;
	private static FileReader fr;
	private static FileWriter fw;
	private static int option;
	private static Scanner scan;
	private static File f; 

	
	public static void main(String[] args) throws Exception 
	{
		scan = new Scanner(System.in);
		System.out.println("WELCOME TO LOCKEDME APP developed by Siddu");
		System.out.println("");
		System.out.println("************************************************************************");
		System.out.println("");
		welcomeScreen();
	}
	
	public static void welcomeScreen() throws Exception
	{

		do
		{
			
			System.out.println("Please Type *Register* for New registration else type *Login* or *Exit* ");
		    String input = scan.nextLine();

			if(input.equalsIgnoreCase("register"))
			{
				System.out.println("Enter Username to register: ");
				String rusername = scan.nextLine();
				System.out.println("Enter Password to register: ");
				String rpassword = scan.nextLine();		
				register(rusername,rpassword);
			}

			else if (input.equalsIgnoreCase("login"))
			{
				System.out.println("Enter Username to login: ");
				String lusername = scan.nextLine();
				System.out.println("Enter Password to login: ");
				String lpassword = scan.nextLine();
				login(lusername,lpassword);
				break;
			}
			else if(input.equalsIgnoreCase("exit"))
			{
				System.out.println("You are Exited !!!!!!!!!!");
				break;
			}
			else
			{
				System.out.println("Invlaid option!!!!! Please select Register or Login");
			}
		} while(true);

	}
	
	public static void register(String run,String rpwd) throws Exception 
	{
		f = new File(run+".txt");
		f.createNewFile();
		fw = new FileWriter(f, true);
		rpw = new PrintWriter(fw);
		rpw.println(run);
		rpw.println(rpwd);
		rpw.close();
		System.out.println("Registration Successful!!!!");
	}
	
	public static void login(String lun, String lpwd) throws Exception
	{
		f = new File(lun+".txt");
		fr = new FileReader(f);
		br = new BufferedReader(fr);
		String line = br.readLine();
		if(line != null)
		{
			if ((line.equalsIgnoreCase(lun)) && (line.equals(lpwd)))
			{
				System.out.println("Login Successfull !!!!!!");
				System.out.println("************************************************");
				System.out.println("Please select one Option in below");
				System.out.println(" 1.ADD\n 2.VIEW\n 3.DELETE\n 4.Exit");
				scan = new Scanner(System.in);
				option = scan.nextInt();
				init(option,lun);
			}
			else
			{
				System.out.println("Invalid User details!!!!");
				welcomeScreen();
			}
		}
		br.close();
	}
	public static void init(int option, String lun) throws Exception 
	{	
		switch (option)
		{
			case 1:
				add(lun);
				break;
			case 2:
				view(lun);
				break;
			case 3:
				delete(lun);
				break;
			case 4:
				exit();
				break;
			
		}
	}

	public static void add(String lun) throws Exception
	{
		scan = new Scanner(System.in);
		System.out.println("Enter Account type and username: ");
		String ac = scan.nextLine();
		String acun = scan.nextLine();		
		System.out.println("Enter Account type password: ");
		String acpw = scan.nextLine();
		fw = new FileWriter(f, true);
		rpw = new PrintWriter(fw);
		rpw.println(ac);
		rpw.println(acun);
		rpw.println(acpw);
		rpw.flush();
		rpw.close();
		System.out.println("Credntials Stored successfully!!!");
		System.out.println("Please select option to be perform Operation :");
		option = scan.nextInt();
		init(option, lun);
	}
	
	public static void view(String lun) throws Exception
	{
		f = new File(lun+".txt");
		fr = new FileReader(f);
		br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null)
		{
			System.out.println(line);
			line = br.readLine();
		}
		br.close();
		option = scan.nextInt();
		init(option,lun);
		System.out.println("Please select option to be perform Operation :");
		option = scan.nextInt();
		init(option, lun);
	}
	
	public static void delete(String lun) throws Exception
	{
		 f = new File(lun+".txt");
		 String p = f.getAbsolutePath();
		try
		{
			f.deleteOnExit();  // deletes file when JVM terminates
			Thread.sleep(2000);
			System.out.println("File deleted Successfuly");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Please select option to be perform Operation :");
		option = scan.nextInt();
		init(option, lun);
	}
	
	public static void exit() throws Exception 
	{
		System.exit(0);
	}

	
}
