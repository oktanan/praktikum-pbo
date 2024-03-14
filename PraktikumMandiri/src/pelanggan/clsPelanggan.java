
package pelanggan;

import java.sql.*;
import javax.swing.JOptionPane;
public class clsPelanggan {
    protected String kode, nama, jk, alamat;
    protected int telp,flag;
    
    public void setKode (String Kd)
    {kode = Kd;}
    public void setNama (String Nm)
    {nama = Nm;}
    public void setJenisKelamin (String Jk)
    {jk = Jk;}
    public void setAlamat (String Al)
    {alamat = Al;}
    public void setTelp (int Tp)
    {telp = Tp;}
    public String getKode()
    {return(kode);}
    public String getNama()
    {return(nama);}
    public String getJenisKelamin()
    {return(jk);}
    public String getAlamat()
    {return(alamat);}
    public int getTelp()
    {return(telp);}
    public void setFlag(int F)
    { flag = F;}
    public int getFlag()
    {return(flag);}
    
   public void simpan()
   {
       try
       {
           Koneksi k=new Koneksi();
           Connection cn=k.getConnection();
           Statement st=cn.createStatement();
           String sql="insert into pelanggan values(";
                  sql+="'"+getKode()+"','"+getNama()+"',";
                  sql+="'"+getJenisKelamin()+"','"+getAlamat()+"',";
                  sql+="'"+getTelp()+"');";
                  
                st.executeUpdate(sql);
                setFlag(1);
                st.close();
                cn.close();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan",
                        "SIMPAN",JOptionPane.INFORMATION_MESSAGE);
       }
       catch (SQLException sqe)
       {
           JOptionPane.showMessageDialog(null, "Error "+sqe.getMessage() );
       }
   }
   
   public void ubah()
   {
       try
       {
           Koneksi k=new Koneksi();
           Connection cn=k.getConnection();
           Statement st=cn.createStatement();
           String sql="update pelanggan set ";
                  sql+="nmplggn='"+getNama()+"',";
                  sql+="jk='"+getJenisKelamin()+"',";
                  sql+="alamat='"+getAlamat()+"',";
                  sql+="telp='"+getTelp()+"'";
                  sql+="where kdplggn='"+getKode()+"'";
             
                st.executeUpdate(sql);
                setFlag(2);
                st.close();
                cn.close();
                JOptionPane.showMessageDialog(null, "Data berhasil diUbah",
                        "UBAH",JOptionPane.INFORMATION_MESSAGE);
       }
       catch (SQLException sqe)
       {
           JOptionPane.showMessageDialog(null, "Error "+sqe.getMessage() );
       }
   }
   public void hapus()
   {
       try
       {
           Koneksi k=new Koneksi();
           Connection cn=k.getConnection();
           Statement st=cn.createStatement();
           String sql="delete from pelanggan ";
                  sql+="where kdplggn='"+getKode()+"'";
                  
                st.executeUpdate(sql);
                setFlag(3);
                st.close();
                cn.close();
                JOptionPane.showMessageDialog(null, "Data berhasil diHapus",
                        "HAPUS",JOptionPane.INFORMATION_MESSAGE); 
       }
       catch (SQLException sqe)
       {
         JOptionPane.showMessageDialog(null,"Data Gagal diHapus",
                   "Gagal Hapus", JOptionPane.WARNING_MESSAGE);  
       }
   }
   public void tampil()
   {
       try
       {
           Koneksi k=new Koneksi();
           Connection cn=k.getConnection();
           Statement st=cn.createStatement();
           String sql="select * from pelanggan ";
                  sql+="where kdplggn='"+getKode()+"'";
           ResultSet rs=st.executeQuery(sql);
           
           if(rs.next())
           {
                setFlag(4);
                setKode(rs.getString("kdplggn"));
                setNama(rs.getString("nmplggn"));
                setJenisKelamin(rs.getString("jk"));
                setAlamat(rs.getString("alamat"));
                setTelp(rs.getInt("telp"));
                st.close();
                rs.close();
           }
       }
       catch(SQLException sqe)
       {}
   }
   public void autoKode(){
       try {
           int hit = 0;
           Koneksi k=new Koneksi();
           Connection cn=k.getConnection();
           Statement st=cn.createStatement();
           String sql="select count(kdplggn)from pelanggan";
           ResultSet rs=st.executeQuery(sql);
           
           if(rs.next()){
               if(Integer.parseInt(rs.getString(1))==0)
               { setKode("B001");
               st.close();
               rs.close();
            }else {
                   sql ="select Max(mid(kdplggn,2,4))from pelanggan";
                   rs = st.executeQuery(sql);
                   rs.next();
                   hit = (Integer.parseInt(rs.getString(1)))+1;
                   if(hit<10){
                       setKode("B00"+hit);                       
                   }else if(hit<100){
                       setKode("B0"+hit);
                   }else if(hit<1000){
                       setKode("B"+hit);
                   }
                   st.close();
                   rs.close();
               }
           }          
       }catch(SQLException sqe){
           JOptionPane.showMessageDialog(null, "Error "+sqe.getMessage() );
       }
   }
}
