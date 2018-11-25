package tomaszkarman.com.domain;

public class Spawarka {
    public String name;
    String model;
    public Integer kod;
    public Boolean status;



    public Spawarka(String name, String model, Integer kod, Boolean status) {
        this.name = name;
        this.model = model;
        this.kod = kod;
        this.status = false;
    }

//    public Spawarka() {
//    }

    public String getName() {
        return name;
    }


    public String getModel() {
        return model;
    }


    public Integer getKod() {
        return kod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setKod(Integer kod) {
        this.kod = kod;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void spawaj () {
        this.status = true;
    }
}

