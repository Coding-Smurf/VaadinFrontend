package org.vaadin.example;

public class Data {
    private int Id;
    private String Name;
    private String Fullname;
    private String Phone;
    private String Email;
    private String Company;
    private String Media;
    private String Sector;
    private String Position;
    private String PersonalAddress;
    private String ProfessionalAddress;
    private String Country;
    private String Community;
    private String Region;
    private String Observations;
    private String SocialMedia;
    private String Clients;

    public Data(int id, String name,String fullname ,String phone, String email, String company, String media, String sector, String position, String personalAddress, String professionalAddress, String country, String community, String region, String observations, String socialMedia, String clients) {
        this.Id = id;
        this.Name = name;
        this.Fullname = fullname;
        this.Phone = phone;
        this.Email = email;
        this.Company = company;
        this.Media = media;
        this.Sector = sector;
        this.Position = position;
        this.PersonalAddress = personalAddress;
        this.ProfessionalAddress = professionalAddress;
        this.Country = country;
        this.Community = community;
        this.Region = region;
        this.Observations = observations;
        this.SocialMedia = socialMedia;
        this.Clients = clients;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getFullname() {
        return Fullname;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getCompany() {
        return Company;
    }

    public String getMedia() {
        return Media;
    }

    public String getSector() {
        return Sector;
    }

    public String getPosition() {
        return Position;
    }

    public String getPersonalAddress() {
        return PersonalAddress;
    }

    public String getProfessionalAddress() {
        return ProfessionalAddress;
    }

    public String getCountry() {
        return Country;
    }

    public String getCommunity() {
        return Community;
    }

    public String getRegion() {
        return Region;
    }

    public String getObservations() {
        return Observations;
    }

    public String getSocialMedia() {
        return SocialMedia;
    }

    public String getClients() {
        return Clients;
    }

}
