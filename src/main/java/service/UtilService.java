package service;

import models.Account;

import java.util.List;

/**
 * Created by Sergey on 19.12.2015.
 */
public class UtilService {
    public static String getCommaText(List<? extends Object> list){

        StringBuilder stringBuilder = new StringBuilder();
        for(Object name : list){
            stringBuilder.append(name.toString()+ ' ' );
        }
        String returnString = stringBuilder.toString().trim();
        returnString = returnString.replace(' ',',');
        return returnString;
    }
    public static String getCommaTextAccount(List<Account> list){

        StringBuilder stringBuilder = new StringBuilder();
        for(Account account : list){
            stringBuilder.append(account.getNumberAccount()+  " ");
        }
        String returnString = stringBuilder.toString().trim();
        returnString = returnString.replace(' ',',');
        return returnString;
    }
}
