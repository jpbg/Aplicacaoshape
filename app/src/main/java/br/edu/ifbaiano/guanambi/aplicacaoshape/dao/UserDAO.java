package br.edu.ifbaiano.guanambi.aplicacaoshape.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
}
