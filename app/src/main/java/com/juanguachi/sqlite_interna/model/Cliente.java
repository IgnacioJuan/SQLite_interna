package com.juanguachi.sqlite_interna.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

public class Cliente {
    private String id;
    private String nombre;
    private String apellido;

    public void agregarCliente(Context mc){
        SqlHelper sqlHelper=new SqlHelper(mc);
        SQLiteDatabase bd=sqlHelper.getWritableDatabase();
        SQLiteStatement ps=null;
        if (bd!=null){
            ps =  bd.compileStatement("insert into clientes values(?,?,?)");
            ps.bindString(1, getId());
            ps.bindString(2, getNombre());
            ps.bindString(3, getApellido());
            ps.execute();
            ps.close();
            //bd.setTransactionSuccessful();
            Toast.makeText(mc,"creado",Toast.LENGTH_LONG).show();
        }
    }
    public void eliminarCliente(Context mc){
        SqlHelper sqlHelper=new SqlHelper(mc);
        SQLiteDatabase bd=sqlHelper.getWritableDatabase();
        SQLiteStatement ps=null;
        if (bd!=null){
            ps =  bd.compileStatement("delete from clientes where id = ? ");
            ps.bindString(1, getId());
            ps.execute();
            ps.close();
            //bd.setTransactionSuccessful();
            Toast.makeText(mc,"Eliminado",Toast.LENGTH_LONG).show();
        }
    }
    public void actualizarCliente(Context mc){
        SqlHelper sqlHelper=new SqlHelper(mc);
        SQLiteDatabase bd=sqlHelper.getWritableDatabase();
        SQLiteStatement ps=null;
        if (bd!=null){
            ps =  bd.compileStatement("update clientes set nombre=?,apellido=? where id=?");
            ps.bindString(3, getId());
            ps.bindString(1, getNombre());
            ps.bindString(2, getApellido());
            ps.execute();
            ps.close();
            //bd.setTransactionSuccessful();
            Toast.makeText(mc,"actualizado",Toast.LENGTH_LONG).show();
        }
    }
    public static Cursor listarClientes(Context mc){
        SqlHelper sqlHelper=new SqlHelper(mc);
        String sql="select _rowid_ as _id,* from clientes";
        return sqlHelper.getReadableDatabase().rawQuery(sql,null);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
