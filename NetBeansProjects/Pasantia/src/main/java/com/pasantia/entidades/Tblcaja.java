package com.pasantia.entidades;
// Generated 6/10/2013 01:37:53 PM by Hibernate Tools 3.2.1.GA



/**
 * Tblcaja generated by hbm2java
 */
public class Tblcaja  implements java.io.Serializable {
    private static final long serialVersionUID = -1558041080892307307L;


     private Integer seccaja;
     private Tblingreso tblingreso;
     private Tblegreso tblegreso;
     private long total;

    public Tblcaja() {
    }

    public Tblcaja(Tblingreso tblingreso, Tblegreso tblegreso, long total) {
       this.tblingreso = tblingreso;
       this.tblegreso = tblegreso;
       this.total = total;
    }
   
    public Integer getSeccaja() {
        return this.seccaja;
    }
    
    public void setSeccaja(Integer seccaja) {
        this.seccaja = seccaja;
    }
    public Tblingreso getTblingreso() {
        return this.tblingreso;
    }
    
    public void setTblingreso(Tblingreso tblingreso) {
        this.tblingreso = tblingreso;
    }
    public Tblegreso getTblegreso() {
        return this.tblegreso;
    }
    
    public void setTblegreso(Tblegreso tblegreso) {
        this.tblegreso = tblegreso;
    }
    public long getTotal() {
        return this.total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }




}


