package com.example.Ba.sket.controllers;

public abstract class ErrorString {

    public static String getErrorString(String strValue,Long id){
        return strValue+" with id: "+id+" not found";
    }
}
