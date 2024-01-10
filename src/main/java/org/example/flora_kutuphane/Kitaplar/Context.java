package org.example.flora_kutuphane.Kitaplar;

public class Context {
    private IState state;

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }
}
