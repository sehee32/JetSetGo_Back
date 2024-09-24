package kr.co.jetsetgo.model;

public class AirportDTO {
    private String code;
    private String city;

    public AirportDTO(String code, String city) {
        this.code = code;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
