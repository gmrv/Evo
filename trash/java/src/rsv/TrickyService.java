package rsv;//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Serebatos
 * Date: 19.07.13
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
public class TrickyService implements Runnable {
    private final String USER_AGENT = "Mozilla/5.0";
    public static final String PATH = "d:\\tmp";
//        public static String URL = "http://localhost:8080/do";
    public static String URL = "http://zubilo.1gb.ru:8080/ms/do";
    private List<File> filesToSend = new ArrayList<File>();
    private boolean sendResult = false;

    @Override
    public void run() {
        while (true) {
            try {
                readFiles();
                sendFiles();
                TimeUnit.SECONDS.sleep(60); //частоту проверки, надо как и путь к серверу и путь к каталогу вынести в параметры проги
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public static void main(String[] args) throws Exception {

        TrickyService tr = new TrickyService();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(tr);
//        tr.readFiles();
//        tr.traceFiles();
//        tr.sendFiles();
//        tr.deleteFiles();
        //        Authenticator.setDefault(new rsv.ProxyAuthenticator("oao\\sap-consultant", "13022012r"));
        //        System.setProperty("http.proxyHost", "10.102.6.50");
        //        System.setProperty("http.proxyPort", "8080");
        //        Proxy p=new Proxy(Proxy.Type.HTTP,new InetSocketAddress("10.102.6.50",8080));


        //        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        //
        //        String r = null;
        //        while ((r = br.readLine()) != null) {
        //            System.out.println(r);
        //        }


    }


//Анализируем указанную директорию, сохраняем все файлы, что там есть в коллекцию
    public void readFiles() {
        setFilesToSend(getFiles(PATH));
    }

    public void traceFiles() {
        for (File file : filesToSend) {
            System.out.println(file);
        }
    }

    public void deleteFiles() {

    }
//если есть что отправить, то отправляем на сервер
//пока заглушка, шлем только строку в параметре, по идее можно xorить текст письма
    public void sendFiles() {
        if (getFilesToSend() != null && !getFilesToSend().isEmpty()) {
            try {
                sendPost();
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        } else {
            System.out.println("Nothing to send");
        }
    }

    public List<File> getFiles(String path) {
        List<File> result;
        File file = new File(PATH);
        File[] files = file.listFiles();
        result = Arrays.asList(files);
        return result;
    }

    public List<File> getFilesToSend() {
        return filesToSend;
    }

    public void setFilesToSend(List<File> filesToSend) {
        this.filesToSend = filesToSend;
    }

    public boolean isSendResult() {
        return sendResult;
    }

    public void setSendResult(boolean sendResult) {
        this.sendResult = sendResult;
    }

    // HTTP POST request - непосредственно отправка, пока тестовая реализация
    private void sendPost() throws Exception {

        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "hm=Test Test Пика пика";     //Тут должна быть почта

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);

        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + URL);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}


class ProxyAuthenticator extends Authenticator {

    private String user, password;

    public ProxyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password.toCharArray());
    }
}