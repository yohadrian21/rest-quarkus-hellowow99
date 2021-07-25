package org.acme.Model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class tag {
    private int idtag;
    private String label;
    public List<post> post = new ArrayList<>();   
    public int getIdtag() {
        return idtag;
    }
    public void setIdtag(int idtag) {
        this.idtag = idtag;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
