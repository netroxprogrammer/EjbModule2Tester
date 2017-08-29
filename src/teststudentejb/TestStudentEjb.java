/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teststudentejb;

import com.test.StudentBean;
import com.test.StudentBeanRemote;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;

public class TestStudentEjb {

   BufferedReader brConsoleReader = null;
   Properties  props;
   InitialContext ctx;
   public TestStudentEjb(){
       try{
       props = new Properties();
       props.load(new  FileInputStream("jndi.properties"));
             props.put("jboss.naming.client.ejb.context", true);
           props.put(ctx.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
       ctx = new InitialContext(props);
       brConsoleReader =  new BufferedReader(new  InputStreamReader(System.in));
       }
       catch(Exception  e){
           e.printStackTrace();
       }
   }
      private void showGUI(){
      System.out.println("**********************");
      System.out.println("Welcome to Book Store");
      System.out.println("**********************");
      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
   }
    public static void main(String[] args) {
        // TODO code application logic here
        TestStudentEjb ejb = new TestStudentEjb();
        ejb.testStateFull();
    }
    public void testStateFull(){
     try{
       /* int  choice =1; 
        final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "MyNewEjb";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = StudentBean.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = StudentBeanRemote.class.getName();
        // let's do the lookup (notice the ?stateful string as the last part of the jndi name for stateful bean lookup)
        StudentBeanRemote student = (StudentBeanRemote) ctx.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
       
       */
        StudentBeanRemote student =  getBean();
         int  choice =1;
           while(choice!=2){
              String studentName;
              showGUI();
              String strChoice = brConsoleReader.readLine();
              choice = Integer.parseInt(strChoice);
              System.out.println("Enter Student Name");
              studentName= brConsoleReader.readLine();
              student.setStudentName(studentName);
             System.out.println(student.testBean("hello This is Test"));
             List<String> list = student.studentList();
             for(String names: list){
                 System.out.println(names);
             }
           }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public StudentBeanRemote getBean() {
   StudentBeanRemote students =null;
        try{
           final String appName="";
           final String moduleName="MyNewEjb";
           final String distinctName="";
           final String beanName = StudentBean.class.getSimpleName();
           final String RemoteInterfaceName=StudentBeanRemote.class.getName();
           students = (StudentBeanRemote)ctx.lookup("ejb:"+appName+"/"+moduleName+"/"+distinctName+"/"+beanName+"!"+RemoteInterfaceName+"?stateful");
                  
       }
       catch(Exception e){
           e.printStackTrace();
       }
       return students;
    }

}
