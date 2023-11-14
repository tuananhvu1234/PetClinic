////
////
////import Entities.*;
////import Models.*;
////import java.sql.Date;
////import java.util.Arrays;
////import java.util.List;
////
//
//public class DefaultData extends Controller<DefaultData> {
//
//    private int a;
//    private int b;
//
//    public DefaultData() {
//    }
//
//    public DefaultData(int a, int b) {
//        this.a = a;
//        this.b = b;
//    }
//
//    public int getA() {
//        return a;
//    }
//
//    public void setA(int a) {
//        this.a = a;
//    }
//
//    public int getB() {
//        return b;
//    }
//
//    public void setB(int b) {
//        this.b = b;
//    }
//
//    @Override
//    public DefaultData setV(DefaultData obj) {
//        obj.setA(1);
//        obj.setB(2);
//        return obj;
//    }
//
//    public void printStar() {
//        System.out.println("*");
//    }
//
//    @Override
//    public String toString() {
//        return "DefaultData{" + "a=" + a + ",b=" + b + '}';
//    }
//
//    public static void main(String[] args) {
//        DefaultData d = new DefaultData();
//        System.out.println(d.abc());
////        System.out.println(d.getInstance());
//    }
////
////    public static void main(String[] args) {
////        
////        BookingEntity bookingE = new BookingEntity();
////        System.out.println(bookingE.getAllBooking("admin"));
////        for (BookingModel booking : bookingE.getAllBooking("admin")){
////            System.out.println(booking);
////        }
////
////        RoleEntity roleM = new RoleEntity();
////        UserEntity userM = new UserEntity();
////        DoctorEntity doctorM = new DoctorEntity();
////        List list;
////        UserModel user;
////        /**
////         * data role
////         */
////        list = Arrays.asList(
////                new RoleModel(1, "Admin"),
////                new RoleModel(2, "Doctor"),
////                new RoleModel(3, "Client")
////        );
////        list.forEach(role -> {
////            System.out.println("Role:add success ? " + roleM.addRole((RoleModel) role));
////        });
////        /**
////         * data admin
////         */
////        user = new UserModel(1,//role
////                "admin", 0, Date.valueOf("2003-01-01"),//user info
////                "admin@admin.petclinic.com", "Ha Noi", "0123456789", //user info
////                new AccountModel("admin", "123456")//account
////        );
////        System.out.println("User:add success ? " + userM.addUser(user));//add any user
////        /**
////         * data doctor
////         */
////        list = Arrays.asList(
////                new DoctorLevelModel(
////                        new UserModel(
////                                "Nguyen Thu Minh", 1, Date.valueOf("2003-11-25"),
////                                "minh.nt.1891@aptechlearning.edu.vn", "Ha Noi", "0368553888",
////                                new AccountModel("leaderThuMinh", "123456")
////                        ),
////                        "titile", "degree", Date.valueOf("2003-01-01")
////                ),
////                new DoctorLevelModel(
////                        new UserModel(
////                                "Doan Hoang Anh", 0, Date.valueOf("2003-04-08"),
////                                "anh.dh.1876@aptechlearning.edu.vn", "Ha Noi", "0911922941",
////                                new AccountModel("memberHoangAnh", "123456")
////                        ),
////                        "titile", "degree", Date.valueOf("2003-01-01")
////                ),
////                new DoctorLevelModel(
////                        new UserModel(
////                                "Vu Tuan Anh", 0, Date.valueOf("2003-01-01"),
////                                "anh.vt.1888@aptechlearning.edu.vn", "Ha Noi", "0367899855",
////                                new AccountModel("memberTuanAnh", "123456")
////                        ),
////                        "titile", "degree", Date.valueOf("2003-01-01")
////                )
////        );
////        list.forEach(dt -> {
////            System.out.println("Doctor:add success ? " + doctorM.addDoctor((DoctorLevelModel) dt));//add doctor (role id = 2)
////        });
////        /**
////         * data client
////         */
////        user = new UserModel(//no user id, no role id
////                "Vuong Quang Huy", 0, Date.valueOf("2003-08-31"),
////                "huy.vq.@aptechlearning.edu.vn", "Ha Noi", "0385897664",
////                new AccountModel("memberQuangHuy", "123456")
////        );
////        System.out.println("User:add success ? " + userM.addClientUser(user));//add client (role id = 3)
////    }
//
//}
