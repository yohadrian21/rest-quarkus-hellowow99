package org.acme.Model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class post {
    private int idpost;
    private String title;
    private String content;
    
    public List<tag> tag = new ArrayList<>();   
    public int getIdpost() {
        return idpost;
    }
    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
