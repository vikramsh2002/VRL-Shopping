package vrl.VRL_EShopping.Security.Classdef;

import vrl.VRL_EShopping.Security.Secure.SecurityAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.stream.Collectors;

public class EncryDecrypt implements SecurityAlgo {

    @Override
    public String encrypt(String p){
        return encrypt1(p,keys);
    }
    @Override
    public String decrypt(String p){
        return decrypt1(p,keys);
    }

    private String encrypt1(String p, String k){
        ArrayList<Long> enc = new ArrayList<>();
        long s_each_itr=0;
        long n=p.length();
        for(int i=0;i<n;++i){
            for(int j=0;j<k.length();++j){
                if(j==0){
                    s_each_itr = ((int)p.charAt(i)) ^ ((int) k.charAt(j));
                }
                else{
                    s_each_itr ^=((int) k.charAt(j));
                }
            }
            enc.add(s_each_itr);
        }

        // add the count
        enc.add(n);
        //System.out.println("Level 1 Cipher : "+enc);
        return encrypt2(enc,k);
    }


    private String encrypt2(ArrayList<Long> p,String k) {

        Collections.reverse(p);
        int k1, k2;
        if (Boolean.parseBoolean(String.valueOf(k.charAt(0)))) {
            k1 = 1;
        } else {
            k1 = 0;
        }
        if (Boolean.parseBoolean(String.valueOf(k.charAt(k.length() - 1)))) {
            k2 = 1;
        }
        else {
            k2=0;
        }

        ArrayList<Long> p1 = (ArrayList<Long>) p.stream().map(e -> (e << k1 << k2)*20).collect(Collectors.toList());
        //System.out.println("Level 2 Cipher : "+p1);
        return encrypt3(p1,k);
    }
    private String encrypt3(ArrayList<Long> p, String k){
        Collections.reverse(p);
        // subtract each element by 500 and then xor then invert ~
        ArrayList<Long> p1 = (ArrayList<Long>) p.stream().map(e -> ~( Integer.parseInt(k)^(e-500)  ) ).collect(Collectors.toList());
        //System.out.println("Level 3 Cipher : "+p1);
        StringBuilder sb = new StringBuilder();
        int count=0;
        for(long i:p1){
            sb.append(i);
            if(count < p1.size()-1)
                sb.append(":");
            count+=1;

        }

//        System.out.println("Level 3 Cipher : "+sb.toString());
        return sb.toString();
    }

    //////////////////======================================================> Convert p str to arraylist long p
    private String decrypt1(String p , String k){
        ArrayList<Long> pp=(ArrayList<Long>)Arrays.asList(p.split(":")).stream().map(e -> Long.parseLong(e)).collect(Collectors.toList());

        ArrayList<Long> p1 =(ArrayList<Long>) pp.stream().map(e-> ( ((~e))^Integer.parseInt(k) )+500).collect(Collectors.toList());
        Collections.reverse(p1);
        //System.out.println("Level 1 Decipher : "+p1);
        return decrypt2(p1,k);
    }

    private String decrypt2(ArrayList<Long>p,String k){

        int k1, k2;
        if (Boolean.parseBoolean(String.valueOf(k.charAt(0)))) {
            k1 = 1;
        } else {
            k1 = 0;
        }
        if (Boolean.parseBoolean(String.valueOf(k.charAt(k.length() - 1)))) {
            k2 = 1;
        }
        else {
            k2=0;
        }

        ArrayList<Long>p1=(ArrayList<Long>)p.stream().map( e -> ((long)e/20)>>k1>>k2).collect(Collectors.toList());
        Collections.reverse(p1);
        //System.out.println("Level 2 Decipher : "+p1);
        return decrypt3(p1,k);
    }

    String decrypt3(ArrayList<Long> p,String k){
        StringBuilder text=new StringBuilder();
        long d=0;
        for(int i=0;i<p.size()-1;++i){
            //System.out.println("I : "+i);
            for(int j=0;j<k.length();++j){
                if(j==0){
                    d=(long)(p.get(i)^(long)k.charAt(j));
                }
                else{
                    d^=((long)k.charAt(j));
                }
            }
            text.append((char)d);
        }
        //System.out.println("Level 3 Decipher : "+text);
        return text.toString();
    }

private final String keys="202002";
}
