package br.edu.ifbaiano.guanambi.aplicacaoshape.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.ifbaiano.guanambi.aplicacaoshape.helper.DBHelper;
import br.edu.ifbaiano.guanambi.aplicacaoshape.model.User;

public class UserDAO {

    private User user;
    private DBHelper db;

    public UserDAO(Context ctx, User user) {
        this.db = new DBHelper(ctx);
        this.user = user;
    }

    public boolean verificarEmailESenha() {

        SQLiteDatabase dbLite = this.db.getReadableDatabase();
        String sql = "SELECT * FROM user where email = ? AND senha = ?";
        Cursor cursor = dbLite.rawQuery(sql,
                new String[]{this.user.getMail(), this.user.getPassword()});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

    }



    public boolean inserir (){
        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",this.user.getName());
        cv.put("senha", this.user.getPassword());
        cv.put("email", this.user.getMail());

        long ret = dbLite.insert("user",
                null,
                cv);

        if (ret > 0){
            return true;
        }
        return false;
    }

    public boolean update(){

        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",this.user.getName());
        cv.put("senha", this.user.getPassword());
        cv.put("email", this.user.getMail());

        long ret = dbLite.update("user", cv,"email = ?", new String[]{this.user.getMail()} );
        if (ret > 0){
            return true;
        }
        return false;
    }

    public boolean delete(){

        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        long ret = dbLite.delete("user","email = ?", new String[]{this.user.getMail()} );

        if (ret > 0){
            return true;
        }
        return false;
    }


    public User obterUserByEmail(){

        SQLiteDatabase dbLite = this.db.getReadableDatabase();
        String sql = "Select * From user where email = ?; ";
        Cursor c = dbLite.rawQuery(sql,new String[]{this.user.getMail()});
        if(c != null){
            c.moveToFirst();
        }

        this.user.setMail(c.getString(0));
        this.user.setPassword(c.getString(1));
        this.user.setName(c.getString(2));

        return this.user;

    }


    private Cursor listarUsers(){

        SQLiteDatabase dbLite = this.db.getReadableDatabase();

        String sql = "SELECT id as _id, nome From user;";
        Cursor c = dbLite.rawQuery(sql,null);

        if(c != null){
            c.moveToFirst();
        }

        return c;
    }


    public ArrayList<User> listarUsersArray(){

        ArrayList<User> list = new ArrayList<>();

        Cursor c = this.listarUsers();

        while (!c.isAfterLast()){
            User u = new User(
                    c.getString(0),
                    c.getString(2),
                    c.getString(1)
            );
            list.add(u);

        }

        return list;
    }

}
