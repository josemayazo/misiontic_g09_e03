package beans.vo;

import java.util.ArrayList;

public class CustomerVo extends UserVo {
    ArrayList<BookingVo> bookingList = new ArrayList<>();

    public CustomerVo(int id, String name, String lastName, String email, String password, String phoneNumber,
            char type, ArrayList<BookingVo> bookingList) {
        super(id, name, lastName, email, password, phoneNumber, type);
        this.bookingList = bookingList;
    }

}
