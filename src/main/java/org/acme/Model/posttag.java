package org.acme.Model;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class posttag {
    private int idpost;
    private int idtag;
    public int getIdpost() {
        return idpost;
    }
    public int getIdtag() {
        return idtag;
    }
    public void setIdtag(int idtag) {
        this.idtag = idtag;
    }
    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

}
