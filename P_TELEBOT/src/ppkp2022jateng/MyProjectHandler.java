/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ppkp2022jateng;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import static ppkp2022jateng.frmTelegram.getMsg;

/**
 *
 * @author Aliv okta
 */
public class MyProjectHandler  extends TelegramLongPollingBot {

    public String msg = null;
    public String id = null;
    
    Connection Con = login.Koneksi.getConnection();
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    
    public MyProjectHandler() {
    }
    
    public void sendPesan (String id, String pesan) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(id);
        sendMessageRequest.setText(pesan);
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            
        }
    }

    @Override
    public String getBotToken() {
        return BotConfig.TOKENMYPROJECT;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            Message message = update.getMessage();
            if(message.hasText()) {
                System.out.println(message.getChat().getFirstName());        
                String pesanM = message.getChatId()+ " : " + message.getText();
                frmTelegram.lstPesan.add(pesanM);
                msg = message.getText();
                id = message.getChatId().toString();
                String name = message.getChat().getUserName();
//                frmTelegram.getMsg(id, msg, name);
                MyProjectHandler a = new MyProjectHandler();
                a.dapatkanPesan(id, msg, name);
            }
        }
    }
    
    public void dapatkanPesan(String id, String msg, String name){
        String pesan = null;
        String query = "SELECT * FROM `tb_keyword` WHERE `keyword`='"+ msg +"'";
        try {
            st = Con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                System.out.println("masuk sini gan");
                pesan = rs.getString("answer");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        frmTelegram.getMsg(id, pesan, name);
    }
    
    @Override
    public String getBotUsername() {
        return BotConfig.USERNAMEMYPROJECT;
    }   
    
}