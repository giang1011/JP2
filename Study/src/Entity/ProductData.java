package Entity;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProductData {
    public int idSanPham;
    public int click;
    public int addToCart;
    public int checkout;
    public LocalDate dateTime;

    public ProductData(int idSanPham, int click, int addToCart, int checkout, LocalDate dateTime) {
        this.idSanPham = idSanPham;
        this.click = click;
        this.addToCart = addToCart;
        this.checkout = checkout;
        this.dateTime = dateTime;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getAddToCart() {
        return addToCart;
    }

    public void setAddToCart(int addToCart) {
        this.addToCart = addToCart;
    }

    public int getCheckout() {
        return checkout;
    }

    public void setCheckout(int checkout) {
        this.checkout = checkout;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
