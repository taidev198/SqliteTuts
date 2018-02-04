package com.example.traig.sqlitetuts;

/**
 * Created by traig on 2/3/2018.
 */

public class favouritItem {
    private String _Content;
    private String _pos;
    private  int _id;
    public favouritItem() {
    }

    public favouritItem(String _Content, String _pos) {
        this._Content = _Content;
        this._pos = _pos;
    }

    public favouritItem(int _id,String _Content, String _pos ) {
        this._Content = _Content;
        this._pos = _pos;
        this._id = _id;
    }

    public void set_Content(String _Content) {
        this._Content = _Content;
    }

    public void set_pos(String _pos) {
        this._pos = _pos;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_Content() {
        return _Content;
    }

    public String get_pos() {
        return _pos;
    }

    public int get_id() {
        return _id;
    }
}
