package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.Model.post;
import org.acme.Model.posttag;
import org.acme.Model.tag;


@Path("/CTCorp")
public class examcrud {
  
    public static List<org.acme.Model.post> gpost = new ArrayList<>();
    public static List<org.acme.Model.tag> gtag = new ArrayList<>();
    
    public static List<org.acme.Model.posttag> gposttag = new ArrayList<>();

    //#region POST
    
    //READPOST
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post/{id}")
    public Response getPostbyId(
        @PathParam("id") int idPost
    )
    {
        org.acme.Model.post Temp = new org.acme.Model.post();
        for(org.acme.Model.post p : gpost)
        {
            if(p.getIdpost()==idPost)
            {
                Temp =p;
               if(p.tag.size()>0)
               {
                for(org.acme.Model.tag t : Temp.tag)
                {
                    for(org.acme.Model.tag gt : gtag)
                    {
                        if(gt.getIdtag()==t.getIdtag())
                        {
                            t.setLabel(gt.getLabel());
                        }
                    }
                }
               }
            }
            
            else{
                Temp = null;
            } 
        }

        return Response.ok(Temp).build();
    }
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post/allpost")
    public Response getPost(
        @PathParam("id") int idPost
    )
    {
        return Response.ok(gpost).build();
    }
    public int findPostId(
        String labelContent
        ,String labelTitle
    )
    {
        int idTag =0;
        for(org.acme.Model.post t : gpost)
        {
            if(t.getContent()==labelContent&&t.getTitle()==labelTitle)
            {
                idTag=t.getIdpost();     
            }
        }

        return idTag;    
    }
    public int findPostbyId(
        int id
    )
    {
        int idPost =0;
        for(org.acme.Model.post t : gpost)
        {
            if(t.getIdpost()==id)
            {
                idPost=t.getIdpost();     
            }
        }

        return idPost;    
    }
    //CREATE POST
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post/create")
    public Response createPost(
        post Data
    )
    {
        try
        {
            int res=0;
        
            for(org.acme.Model.post p : gpost)
            {
                if(p.getIdpost()==Data.getIdpost())
                {
                    throw new Exception("Post with this Id already Exist ");
                }
            }
            if(Data.tag.size()>0)
            {
                throw new Exception("This is for inputing master data only");
            }
            for(org.acme.Model.tag t : Data.tag)
            {
                int tes = findTagId(t.getLabel());
                if(tes==0)
                {
                    throw new Exception("Tag doesnt Exist");
                }
            }

            gpost.add(Data);
    
            return Response.ok(gpost).build();
    
        }
        catch(Exception ex)
        {
            return Response.ok(ex.getMessage()).build();

        }
    }
    //UPDATE POST
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post/update")
    public Response updatePost(
        post Data
    )
    {
        try
        {
            int res=0;
        
            for(org.acme.Model.post p : gpost)
            {
                if(p.getIdpost()==Data.getIdpost())
                {
                    p.setTitle(Data.getTitle());
                    p.setContent(Data.getContent());
                    res=1;
                }
            }
            if(res==0)
            {
                throw new Exception("Data not found");
            }
             return Response.ok("Data Updated").build();
            
    
        }
        catch(Exception ex)
        {
            return Response.ok(ex.getMessage()).build();

        }
    }
    //DELETE POST
    @GET    
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/post/del/{id}")
    public Response delPost(
        @PathParam("id") String idPost
    )
    { try
        {
            int res=0;
        
            for(org.acme.Model.post p : gpost)
            {
                if(p.getIdpost()==Integer.parseInt(idPost))
                {
                   gpost.remove(p);
                   res=1;
                   if(gpost.size()<=0)
                   {
                       break;
                   }
                }
            }
            for(org.acme.Model.tag p : gtag)
            {
                for(org.acme.Model.post q : p.post)
                {
                    if(q.getIdpost()==Integer.parseInt(idPost))
                    {
                       p.post.remove(q);
                       res=1;
                       
                       if(p.post.size()<=0)
                       {
                           break;
                       }
                    }
                }
            }
            if(res==0)
            {
                throw new Exception("Data not found");
            }
             return Response.ok("Data Deleted").build();
            
    
        }
        catch(Exception ex)
        {
            return Response.ok(ex.getMessage()).build();

        }
    }


    //#endregion
    
    //#region TAG
    
    //READ TAG
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tag/{id}")
    public Response getTagbyId(
        @PathParam("id") int idTag
    )
    {
        org.acme.Model.tag Temp = new org.acme.Model.tag();
        for(org.acme.Model.tag p : gtag)
        {
            if(p.getIdtag()==idTag)
            {
                Temp =p;
               if(p.post.size()>0)
               {
                for(org.acme.Model.post t : Temp.post)
                {
                    for(org.acme.Model.post gt : gpost)
                    {
                        if(gt.getIdpost()==t.getIdpost())
                        {
                            t.setContent(gt.getContent());
                            t.setTitle(gt.getTitle());
                        }
                    }
                }
               }
            }
            else{
                Temp = null;
            } 
        }
        return Response.ok(Temp).build();
    }
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tag/alltag")
    public Response getTag(
    )
    {
        List<String> res = new ArrayList<>();
        return Response.ok(gtag).build();
    }

    public int findTagId(
        String labelContent
    )
    {
        int idTag =0;
        for(org.acme.Model.tag t : gtag)
        {
            if(t.getLabel()==labelContent)
            {
                idTag=t.getIdtag();     
            }
        }

        return idTag;    
    }

    public int findTagbyId(
        int id
    )
    {
        int idTag =0;
        for(org.acme.Model.tag t : gtag)
        {
            if(t.getIdtag()==id)
            {
                idTag=t.getIdtag();     
            }
        }

        return idTag;    
    }
    //CREATE TAG
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/tag/create")
    public Response createTag(
        tag Data
    )
    {
        try
        {
            int res=0;
            for(org.acme.Model.tag p : gtag)
            {
                if(p.getIdtag()==Data.getIdtag())
                {
                    throw new Exception("Tag with this Id already Exist ");
                }
            }
            if(Data.post.size()>0)
            {
                throw new Exception("This is for inputing master data only");
            }
            for(org.acme.Model.post t : Data.post)
            {
                int tes = findPostId(t.getContent(),t.getTitle());
                if(tes==0)
                {
                    throw new Exception("Post doesnt Exist");
                }
            }
            gtag.add(Data);
            return Response.ok(gtag).build();
    
        }
        catch(Exception ex)
        {
            return Response.ok(ex.getMessage()).build();
        }
    }

    public Response addPosttoTag(
        tag Data,post Data2
    )
    {
        for(org.acme.Model.tag t : gtag)
        {
            if(t.getIdtag()==Data.getIdtag())
            {
                t.post.add(Data2);
            }
        }

        return Response.ok("Already deleted").build();
    }

    //UPDATE TAG
     //UPDATE POST
     @POST    
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     @Path("/tag/update")
     public Response updateTag(
         tag Data
     )
     {
         try
         {
             int res=0;
         
             for(org.acme.Model.tag p : gtag)
             {
                 if(p.getIdtag()==Data.getIdtag())
                 {
                     p.setLabel(Data.getLabel());
                     res=1;
                 }
             }
             if(res==0)
             {
                 throw new Exception("Data not found");
             }
              return Response.ok("Data Updated").build();
             
     
         }
         catch(Exception ex)
         {
             return Response.ok(ex.getMessage()).build();
 
         }
     }
    //DELETE TAG
    @GET    
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/tag/del/{id}")
    public Response delTag(
        @PathParam("id") String idTag
    )
    { try
        {
            int res=0;
        
            for(org.acme.Model.tag p : gtag)
            {
                if(p.getIdtag()==Integer.parseInt(idTag))
                {
                    gtag.remove(p);
                   res=1;
                   if(gtag.size()<=0)
                   {
                       break;
                   }
                }
            }
            for(org.acme.Model.post p : gpost)
            {
                for(org.acme.Model.tag q : p.tag)
                {
                    if(q.getIdtag()==Integer.parseInt(idTag))
                    {
                       p.tag.remove(q);
                       res=1;
                       
                       if(p.tag.size()<=0)
                       {
                           break;
                       }
                    }
                }
            }
            if(res==0)
            {
                throw new Exception("Data not found");
            }
             return Response.ok("Data Deleted").build();
            
    
        }
        catch(Exception ex)
        {
            return Response.ok(ex.getMessage()).build();

        }
    }
    //#endregion
    
    //#region POSTTAG
    
    //READ POSTTAG


    //CREATE POSTTAG
    public int CreatePostTag (posttag Data)
    {
        int ret =0;
        try{
            gposttag.add(Data);
            ret=1;
        }
        catch(Exception ex)
        {
            
        }
        return ret;
    }
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post/{idpost}/tag/{idtag}")
    public Response CreatePostTag(
        @PathParam("idpost") int idPost,
        @PathParam("idtag") int idTag
    )
    {
        try
        {
            int res=0;
        
            for(org.acme.Model.post p : gpost)
            {
                if(p.getIdpost()==idPost)
                {
                    int tes = findTagbyId(idTag);
                    if(tes==0)
                    {
                        throw new Exception("Tag doesnt Exist");
                    }
                    org.acme.Model.tag tagid = new org.acme.Model.tag();
                    tagid.setIdtag(idTag);
                    if(p.tag.size()>0)
                    {
                        for(org.acme.Model.tag t : p.tag)
                        {
                            if(t.getIdtag()==idTag)
                            {
                                throw new Exception("Tag Already Exist");
                            }
                        }
                        p.tag.add(tagid);
                    }
                    else
                    {
                        p.tag.add(tagid);
                    }
                    res=1;
                }
            }
            if(res==1)
            {
                for(org.acme.Model.tag p : gtag)
                {
                    if(p.getIdtag()==idTag)
                    {
                        
                        org.acme.Model.post postid = new org.acme.Model.post();
                        postid.setIdpost(idPost);
                        if(p.post.size()>0)
                        {
                            for(org.acme.Model.post t : p.post)
                            {
                                if(t.getIdpost()==idPost)
                                {
                                    throw new Exception("Post Already Exist");
                                }
                            }
                            p.post.add(postid);
                        }
                        else
                        {
                            p.post.add(postid);
                        }
    
                    }
                }
            }
            else
            {
                throw new Exception("Post Doesnt Exist");
            }
           
            return Response.ok(gpost).build();
    
        }
        catch(Exception ex)
        {
            return Response.ok(ex.getMessage()).build();

        }
    }
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/tag/{idtag}/post/{idpost}")
    public Response CreateTagPost(
        @PathParam("idpost") int idPost,
        @PathParam("idtag") int idTag
    )
    {
        try
        {
            int res=0;
        
            for(org.acme.Model.tag p : gtag)
            {
                if(p.getIdtag()==idTag)
                {
                    int tes = findPostbyId(idPost);
                    if(tes==0)
                    {
                        throw new Exception("Post doesnt Exist");
                    }
                    org.acme.Model.post postid = new org.acme.Model.post();
                    postid.setIdpost(idPost);
                    if(p.post.size()>0)
                    {
                        for(org.acme.Model.post t : p.post)
                        {
                            if(t.getIdpost()==idPost)
                            {
                                throw new Exception("Post Already Exist");
                            }
                        }
                        p.post.add(postid);
                    }
                    else
                    {
                        p.post.add(postid);
                    }
                    res=1;
                }
            }
            
           
            if(res==1)
            {
                for(org.acme.Model.post p : gpost)
                {
                    if(p.getIdpost()==idPost)
                    {
                        
                        org.acme.Model.tag tagid = new org.acme.Model.tag();
                        tagid.setIdtag(idTag);
                        if(p.tag.size()>0)
                        {
                            for(org.acme.Model.tag t : p.tag)
                            {
                                if(t.getIdtag()==idTag)
                                {
                                    throw new Exception("Tag Already Exist");
                                }
                            }
                            p.tag.add(tagid);
                        }
                        else
                        {
                            p.tag.add(tagid);
                        }
    
                    }
                }
            }
            else
            {
                throw new Exception("Tag Doesnt Exist");
            }
           
            return Response.ok(gpost).build();
    
        }
        catch(Exception ex)
        {
            return Response.ok(ex.getMessage()).build();

        }
    }
    //#endregion
    
    //#region Testing
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hellocount")
    public String hellocount() {
        return "Count 1 2 3";
    }

    // @GET
    // @Produces(MediaType.TEXT_PLAIN)
    // @Path("/hellocount")
    // public String hellocount() {
    //     return "Count 1 2 3";
    // }
    //#endregion
    
}
