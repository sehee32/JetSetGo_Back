package kr.co.jetsetgo.model;

public class Airport {
    private String code; // IATA 코드
    private String name; // 공항 이름
    private String city; // 도시 이름
    private String countryName; // 국가 이름

    // 기본 생성자
    public Airport() {}

    // 모든 필드를 포함한 생성자
    public Airport(String code, String name, String city, String countryName) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.countryName = countryName;
    }

    // Getter 및 Setter
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
