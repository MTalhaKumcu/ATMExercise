package ATM;


import java.util.Scanner;

public class ATM {
    /*
     *
     * kullanicidan giris icin kart no ve sifre iste (yanlis girdiginde tekrarlasin) kartno 10 / sifre 4
     *
     * kart nosunda bosluklar dogru kabul edilsin
     *
     * kart nosu ve sifre girildiginde menu acilsin
     *   menu
     *       bakiye sorgulama , para yatirma , para cekme , para gonderme , sifre degistirme, ve cikis
     * para cekmede ve gondermede mevcut bakiyeden buyuk para cekilmez ve gonderilmez
     *
     * para gondermede Iban PL ile baslamali ve toplam 10 karakter olmali, eger iban uygun degilse tekrar denemesini iste 3`uncude ana menuye donsun
     *
     * sifre degistirmeden once mevcute sifreyi istemeli
     *
     * */

    private double bakiye = 100;
    static String kartNo = "1234567890";
    static String pass = "1234";
    static String senderIBAN;
    static String receiverIBAN;
    Scanner scanner = new Scanner(System.in);


    public void welcome() {
        System.out.println("Welcome dear member!");

        System.out.println("Card No :");
        String welcomeCardNo = scanner.nextLine();
        System.out.println("Passsword :");
        String welcomePass = scanner.nextLine();

        welcomeCardNo = welcomeCardNo.replaceAll("\\s", "");
        if (welcomeCardNo.equals(kartNo) && welcomePass.equals(pass)) {
            menu();
        } else {
            System.out.println("Please invalid password or kartno");
            welcome();
        }

    }


    public void menu() {

        int section = scanner.nextInt();

        System.out.println("1-bakiye sorgulama:\n " +
                "2-para yatirma:\n " +
                "3-para cekme:\n " +
                "4-para gonderme:\n " +
                "5-sifre degistirme:\n " +
                "6-cikis");


        switch (section) {

            case 1:
                bakiyeSorgulama();
                break;
            case 2:
                System.out.println("yatirilmak istenen miktar");
                double miktar = scanner.nextInt();
                paraYatirma(miktar);
                break;
            case 3:
                System.out.println("cekilmek istenen miktar");
                double cekilenMiktar = scanner.nextInt();
                paraCekme(cekilenMiktar);
                break;
            case 4:
                paraGonderme();
                break;
            case 5:
                String newPass = scanner.nextLine();
                sifreDegistirme(newPass);
                break;
            case 6:
                exit();
            default:
                System.out.println("invalid option");
                menu();
        }


    }

    public void bakiyeSorgulama() {
        System.out.println("bakiye: " + bakiye);
        System.out.println("yapilmak istenen islem");
        menu();
    }

    public void paraYatirma(double miktar) {
        bakiye += miktar;
        System.out.println("Para basariyla yatirildi");
        bakiyeSorgulama();
        System.out.println("yapilmak istenen islem");
        menu();
    }

    public void paraCekme(double cekilenMiktar) {
        if (cekilenMiktar <= bakiye) {
            bakiye -= cekilenMiktar;
            bakiyeSorgulama();
            System.out.println("yapilmak istenen islem");

        } else {
            System.out.println("yetersiz bakiye");
            bakiyeSorgulama();

            System.out.println("yeniden deneyin");
            double yeniMiktar = scanner.nextInt();
            paraCekme(yeniMiktar);
            System.out.println("yapilmak istenen islem");

        }
    }

    public void paraGonderme() {

        System.out.println("Gonderilmek istene miktar: ");
        double gonderilenParaMiktari = scanner.nextDouble();

        if (gonderilenParaMiktari <= bakiye) {
            bakiye -= gonderilenParaMiktari;

            System.out.println("Please enter sender IBAN: ");
            senderIBAN = scanner.next(); // Use next() for single word input
            scanner.nextLine(); // Consume the newline character left by next()

            System.out.println("Please enter receiver IBAN: ");
            receiverIBAN = scanner.nextLine();

            System.out.println("para " + senderIBAN + "`dan " + receiverIBAN + "`a gonderildi");
            bakiyeSorgulama();

        } else {

            System.out.println("yetersiz bakiye");
            bakiyeSorgulama();
        }

    }


    public void sifreDegistirme(String newPass) {
        System.out.println("suanki passwordu girin");
        pass = scanner.nextLine();
        System.out.println("yeni password girin: ");
        newPass = scanner.nextLine();
        pass = newPass;
        System.out.println("password changed! ");
    }

    public void exit() {
        System.out.println("Thank you!");
        System.exit(0);
    }

}

