package pojos;

public class BookingDatesPojo {

    //1-Tum Key'ler icin private Variable'lar olusturduk
    private String checkin;
    private String checkout;

    //2-Tum parametrelerle ve parametresiz constructor lar olusturduk


    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {
    }

    //3-Public Getter ve Setter methodlarini olusturduk


    public String getCheckin() {

        return checkin;
    }

    public void setCheckin(String checkin) {

        this.checkin = checkin;
    }

    public String getCheckout() {

        return checkout;
    }

    public void setCheckout(String checkout) {

        this.checkout = checkout;
    }

    //4-To String methodunu olusturuyoruz

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
