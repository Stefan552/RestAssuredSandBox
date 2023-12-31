package com.example.sandbox;

public class Endpoints {

    static final String baseUrl = "https://petstore.swagger.io/v2";

    //-------------------------pet-------------------------
    public static final String findByStatus = "/pet/findByStatus";
    public static final String uploadImage = "/uploadImage";
    public static final String newPet = "/pet";
    public static final String petById = "/pet/";
    public  static  final String deletePet ="/pet/";


    //-------------------------store-------------------------
    public static final String order = "/store/order";
    public static final String inventory = "/store/inventory";
    public static final String orderById= "/store/order/";
    public static final String deleteOrder="/store/order/";

    //-------------------------user-------------------------

    public static final String createWithArray = "/user/createWithArray";
    public static final String deleteUser = "/user/";
    public static final String userByUsername = "/user/";
    public static final String login = "/user/login";
    public static final String logout = "/user/logout";
    public static final String createUserSimple="/user";










}
