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


@Path("/hello")
public class helowow99 {

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
        List<String> res = new ArrayList<>();
        res = ReadPostTag(idPost, 0);
        //return Response.ok(res).build();
        // for (org.acme.Model.post lpost :gpost)
        // {
        //     if(lpost.getIdpost()==(idPost))
        //     {
        //         return Response.ok(lpost).build();
        //     }
        // }
        return Response.ok(res).build();
    }
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post/allpost")
    public Response getPost(
        @PathParam("id") int idPost
    )
    {
        List<String> res = new ArrayList<>();
        //res = ReadPostTag(Integer.parseInt(idTag), 0);
        //return Response.ok(res).build();
        
        return Response.ok(gpost).build();
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
                    throw new Exception();
                }
            }
    
          
            //FILL to POST TAG
            for(org.acme.Model.tag t : Data.tag)
            {
                int tes = findTagId(t.getLabel());
                org.acme.Model.tag ltag = new org.acme.Model.tag();
                org.acme.Model.post lpost2 = new org.acme.Model.post();
                ltag.setIdtag(t.getIdtag());
                ltag.setLabel(t.getLabel());
                lpost2.setIdpost(Data.getIdpost());
                lpost2.setContent(Data.getContent());
                lpost2.setTitle(Data.getTitle());
                ltag.post.add(lpost2);
                createTag(ltag);
                // org.acme.Model.posttag lposttag = new org.acme.Model.posttag();
                // lposttag.setIdpost(Data.getIdpost());
                // lposttag.setIdtag(t.getIdtag());
                // CreatePostTag(lposttag);
            }
            gpost.add(Data);
    
            return Response.ok(gpost).build();
    
        }
        catch(Exception ex)
        {
            return Response.ok("failed").build();

        }
    }
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post/create2")
    public Response createPost2(
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
                    throw new Exception();
                }
            }   
            gpost.add(Data);
            return Response.ok(gpost).build();
    
        }
        catch(Exception ex)
        {
            return Response.ok("failed").build();

        }
    }
    public Response addTagtoPost(
        tag Data,post Data2
    )
    {
        for(org.acme.Model.post t : gpost)
        {
            if(t.getIdpost()==Data2.getIdpost())
            {
                t.tag.add(Data);
            }
        }

        return Response.ok("Already deleted").build();
    }

    //UPDATE POST
    //DELETE POST
    @GET    
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/post/del/{id}")
    public Response delPost(
        @PathParam("id") String idPost
    )
    {
        int res=0;
        res = DeletePostTag(Integer.parseInt(idPost), 1);
        return Response.ok("Already deleted").build();
    }


    //#endregion
    
    //#region TAG
    
    //READ TAG
    @GET    
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/tag/{id}")
    public Response getTag(
        @PathParam("id") String idTag
    )
    {
        List<String> res = new ArrayList<>();
        res = ReadPostTag(Integer.parseInt(idTag), 0);
        return Response.ok(res).build();
    }
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tag/alltag")
    public Response getTag(
    )
    {
        List<String> res = new ArrayList<>();
        //res = ReadPostTag(Integer.parseInt(idTag), 0);
        //return Response.ok(res).build();
        
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

    //CREATE TAG
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/tag/create")
    public Response createTag(
        tag Data
    )
    {
        int res=0;
        gtag.add(Data);
        return Response.ok("Already deleted").build();
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


    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/tag/create2")
    public Response createTag2(
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
                    throw new Exception();
                }
            }   
            gtag.add(Data);
            return Response.ok(gtag).build();
    
        }
        catch(Exception ex)
        {
            return Response.ok("failed").build();

        }
    }
    //UPDATE TAG
    //DELETE TAG
    @GET    
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/tag/del/{id}")
    public Response delTag(
        @PathParam("id") String idTag
    )
    {
        int res=0;
        res = DeletePostTag(Integer.parseInt(idTag), 1);
        return Response.ok("Already deleted").build();
    }
    //#endregion
    
    //#region POSTTAG
    
    //READ POSTTAG
    public List<String> ReadPostTag(int id,int postortag)
    {
        List<String> res = new ArrayList<>();
        try {
            if(postortag==0)//this is read from post to search tag
            {
            gposttag.forEach(pt ->{
                if(pt.getIdpost()==id)
                {
                    gtag.forEach(t->
                    {
                        if(t.getIdtag()==pt.getIdtag())
                        {
                            res.add(t.getLabel());
                        }
                    });
                }
            }); 
            }
            else //this is read from tag to post
            {
                gposttag.forEach(pt ->{
                    if(pt.getIdtag()==id)
                    {
                        gpost.forEach(p->
                        {
                            if(p.getIdpost()==pt.getIdpost())
                            {
                                res.add(p.getTitle() + " "+p.getContent());
                            }
                        });
                    }
                });
            }
            
        }
        catch(Exception ex)
        {
            
        }
        return res;
    }

    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post/allposttag")
    public Response getPostTag(
    )
    {
        List<Integer> res = new ArrayList<>();
        //res = ReadPostTag(Integer.parseInt(idTag), 0);
        //return Response.ok(res).build();
        // gposttag.forEach(pt ->{
        //     if(pt.getIdpost()==1)
        //     {
        //         gtag.forEach(t->
        //         {
        //             if(t.getIdtag()==pt.getIdtag())
        //             {
        //                 res.add(t.getLabel());
        //             }
        //         });
        //     }
        // });
        //return Response.ok(res).build();
        
        for(posttag pt:gposttag)
        {
            if(pt.getIdpost()==1)
            {
                res.add(pt.getIdtag());
            }
        }
        return Response.ok(gposttag).build();
    }


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
    //UPDATE POSTTAG
    //DELETE POSTTAG
    public int DeletePostTag (int id,int postortag)
    {
        int ret =0;
        try{
            if(postortag==0)
            {
                Optional<posttag> posttagdel = gposttag.stream().filter(postag->postag.getIdpost() ==id).findFirst();
                if(posttagdel.isPresent())
                {
                    boolean removed = gposttag.remove(posttagdel.get());
                }
            }
            else
            {
                Optional<posttag> posttagdel = gposttag.stream().filter(postag->postag.getIdtag() ==id).findFirst();
                if(posttagdel.isPresent())
                {
                    boolean removed = gposttag.remove(posttagdel.get());
                }
            }
        }
        catch(Exception ex)
        {
            
        }
        return ret;
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