package net.dewep.intranetepitech.api;

public final class Configurations {

    public static String getFullHost() {
        return "https://" + getHost();
    }

    public static String getHost() {
        return "intra.epitech.eu";
    }

    public static String getUrlLogin() {
        return getFullHost() + "/?format=json";
    }

    public static String getUrlMark(String login) {
        return getFullHost() + "/user/" + login + "/notes/?format=json";
    }

    public static String getUrlCalendar() {
        return getFullHost() + "/planning/load?format=json";
    }

    public static String getUrlProfilPicture(String login) {
        return "https://cdn.local.epitech.eu/userprofil/profilview/" + login + ".jpg";
    }

}
