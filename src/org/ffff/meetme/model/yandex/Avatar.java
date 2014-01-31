package org.ffff.meetme.model.yandex;

/**
 * Created by flicus on 27.01.14.
 */
public class Avatar {
    private String SnippetSquare;
    private String MiddleRound50;
    private String BigRound;
    private String BigSquare;
    private String Portrait;

    public Avatar() {
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "SnippetSquare='" + SnippetSquare + '\'' +
                ", MiddleRound50='" + MiddleRound50 + '\'' +
                ", BigRound='" + BigRound + '\'' +
                ", BigSquare='" + BigSquare + '\'' +
                ", Portrait='" + Portrait + '\'' +
                '}';
    }

    public String getSnippetSquare() {
        return SnippetSquare;
    }

    public void setSnippetSquare(String snippetSquare) {
        SnippetSquare = snippetSquare;
    }

    public String getMiddleRound50() {
        return MiddleRound50;
    }

    public void setMiddleRound50(String middleRound50) {
        MiddleRound50 = middleRound50;
    }

    public String getBigRound() {
        return BigRound;
    }

    public void setBigRound(String bigRound) {
        BigRound = bigRound;
    }

    public String getBigSquare() {
        return BigSquare;
    }

    public void setBigSquare(String bigSquare) {
        BigSquare = bigSquare;
    }

    public String getPortrait() {
        return Portrait;
    }

    public void setPortrait(String portrait) {
        Portrait = portrait;
    }
}
