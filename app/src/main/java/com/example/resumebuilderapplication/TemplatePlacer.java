package com.example.resumebuilderapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TemplatePlacer extends AppCompatActivity {

WebView webView;
    String templateToShow,loggedInUserName,templateNo;
    String firstName,lname,email,phoneNumber,skills,objective,experience,CGPA,college,degreeName,website;
    Integer cgpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.templateplacer);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.loggedInUserName = extras.getString("loggedInUserName");
            this.templateNo=extras.getString("templateNo");
        }
        DBHelper db=new DBHelper(this);
        Cursor resumeCursor=db.getReadableDatabase().query("resumes",null,"userName=?",new String[]{loggedInUserName},null,null,null);
        Cursor userCursor=db.getReadableDatabase().query("users",null,"userName=?",new String[]{loggedInUserName},null,null,null);
        if(resumeCursor.moveToFirst() && userCursor.moveToFirst()){
            //Getting data from User Table.
            firstName=userCursor.getString(userCursor.getColumnIndex("firstName"));
            lname=userCursor.getString(userCursor.getColumnIndex("lastName"));
            phoneNumber=userCursor.getString(userCursor.getColumnIndex("phoneNumber"));
            email=userCursor.getString(userCursor.getColumnIndex("email"));

            //Get Data From Resume Table
            experience=resumeCursor.getString(resumeCursor.getColumnIndex("experience"));
            college=resumeCursor.getString(resumeCursor.getColumnIndex("college"));
            degreeName=resumeCursor.getString(resumeCursor.getColumnIndex("degreeName"));
            objective=resumeCursor.getString(resumeCursor.getColumnIndex("objective"));
            skills=resumeCursor.getString(resumeCursor.getColumnIndex("skills"));
            website=resumeCursor.getString(resumeCursor.getColumnIndex("website"));
            cgpa=(resumeCursor.getInt(resumeCursor.getColumnIndex("cgpa")));
            CGPA=cgpa.toString();
            if(templateNo.equals("3")){
                 templateToShow="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                        "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                        "\n" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n" +
                        "\n" +
                        "<head>\n" +
                        "     <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" +
                        "\n" +
                        "     <title>"+firstName+" "+lname+",s Resume Preview"+"</title>\n" +
                        "\n" +
                        "     <style type=\"text/css\">\n" +
                        "        * { margin: 0; padding: 0; }\n" +
                        "        body { font: 16px Helvetica, Sans-Serif; line-height: 24px; background: url(images/noise.jpg); }\n" +
                        "        .clear { clear: both; }\n" +
                        "        #page-wrap { width: 800px; margin: 40px auto 60px; }\n" +
                        "        #pic { float: right; margin: -30px 0 0 0; }\n" +
                        "        h1 { margin: 0 0 16px 0; padding: 0 0 16px 0; font-size: 42px; font-weight: bold; letter-spacing: -2px; border-bottom: 1px solid #999; }\n" +
                        "        h2 { font-size: 20px; margin: 0 0 6px 0; position: relative; }\n" +
                        "        h2 span { position: absolute; bottom: 0; right: 0; font-style: italic; font-family: Georgia, Serif; font-size: 16px; color: #999; font-weight: normal; }\n" +
                        "        p { margin: 0 0 16px 0; }\n" +
                        "        a { color: #999; text-decoration: none; border-bottom: 1px dotted #999; }\n" +
                        "        a:hover { border-bottom-style: solid; color: black; }\n" +
                        "        ul { margin: 0 0 32px 17px; }\n" +
                        "        #objective { width: 500px; float: left; }\n" +
                        "        #objective p { font-family: Georgia, Serif; font-style: italic; color: #666; }\n" +
                        "        dt { font-style: italic; font-weight: bold; font-size: 18px; text-align: right; padding: 0 26px 0 0; width: 150px; float: left; height: 100px; border-right: 1px solid #999;  }\n" +
                        "        dd { width: 600px; float: right; }\n" +
                        "        dd.clear { float: none; margin: 0; height: 15px; }\n" +
                        "     </style>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\n" +
                        "    <div id=\"page-wrap\">\n" +
                        "    \n" +
                        "        <img src=\"file:///android_asset/template1/images/cthulu.png\" alt=\"Photo of Cthulu\" id=\"pic\" />\n" +
                        "    \n" +
                        "        <div id=\"contact-info\" class=\"vcard\">\n" +
                        "        \n" +
                        "            <!-- Microformats! -->\n" +
                        "        \n" +
                        "            <h1 class=\"fn\">"+firstName+" "+lname+"</h1>\n" +
                        "        \n" +
                        "            <p>\n" +
                        "                Cell: <span class=\"tel\">"+phoneNumber+"</span><br />\n" +
                        "                Email: <a class=\"email\" href=\""+email+"\">"+email+"</a>\n" +
                        "            </p>\n" +
                        "        </div>\n" +
                        "                \n" +
                        "        <div id=\"objective\">\n" +
                        "            <p>\n" +
                        "               "+objective+"\n" +
                        "            </p>\n" +
                        "        </div>\n" +
                        "        \n" +
                        "        <div class=\"clear\"></div>\n" +
                        "        \n" +
                        "        <dl>\n" +
                        "            <dd class=\"clear\"></dd>\n" +
                        "            \n" +
                        "            <dt>Education</dt>\n" +
                        "            <dd>\n" +
                        "                <h2>"+college+"</h2>\n" +
                        "                <p><strong>Major:</strong>"+degreeName+"<br />\n" +

                        "            </dd>\n" +
                        "            \n" +
                        "            <dd class=\"clear\"></dd>\n" +
                        "            \n" +
                        "            <dt>Skills</dt>\n" +
                        "            <dd>\n" +
                        "                <h2>"+skills+"</h2>\n" +
                        "            </dd>\n" +
                        "            \n" +
                        "            <dd class=\"clear\"></dd>\n" +
                        "            \n" +
                        "            <dt>Experience</dt>\n" +
                        "            <dd>\n" +
                        "                <h2>"+experience+"</h2>\n" +

                        "            \n" +

                        "            <dd class=\"clear\"></dd>\n" +
                        "            \n" +
                        "            <dt>References</dt>\n" +
                        "            <dd>Available on request</dd>\n" +
                        "            \n" +
                        "            <dd class=\"clear\"></dd>\n" +
                        "        </dl>\n" +
                        "        \n" +
                        "        <div class=\"clear\"></div>\n" +
                        "    \n" +
                        "    </div>\n" +
                        "\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";
            }
            else if(templateNo.equals("2")){
                templateToShow="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\n" +
                        "\t<title>"+firstName+" "+lname+" | "+email+"</title>\n" +
                        "\t<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n" +
                        "\n" +
                        "\t<meta name=\"keywords\" content=\"\" />\n" +
                        "\t<meta name=\"description\" content=\"\" />\n" +
                        "\n" +
                        "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"http://yui.yahooapis.com/2.7.0/build/reset-fonts-grids/reset-fonts-grids.css\" media=\"all\" /> \n" +
                        "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/template2/resume.css\" media=\"all\" />\n" +
                        "\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<div id=\"doc2\" class=\"yui-t7\">\n" +
                        "\t<div id=\"inner\">\n" +
                        "\t\n" +
                        "\t\t<div id=\"hd\">\n" +
                        "\t\t\t<div class=\"yui-gc\">\n" +
                        "\t\t\t\t<div class=\"yui-u first\">\n" +
                        "\t\t\t\t\t<h1>"+firstName+" "+lname+"</h1>\n" +
                        "\t\t\t\t\t<h2>JOB</h2>\n" +
                        "\t\t\t\t</div>\n" +
                        "\n" +
                        "\t\t\t\t<div class=\"yui-u\">\n" +
                        "\t\t\t\t\t<div class=\"contact-info\">\n" +
                        "\t\t\t\t\t\t<h3><a id=\"pdf\" href=\"#\">Download PDF</a></h3>\n" +
                        "\t\t\t\t\t\t<h3><a href=\"mailto:"+email+"\">"+email+"</a></h3>\n" +
                        "\t\t\t\t\t\t<h3>"+phoneNumber+"</h3>\n" +
                        "\t\t\t\t\t</div><!--// .contact-info -->\n" +
                        "\t\t\t\t</div>\n" +
                        "\t\t\t</div><!--// .yui-gc -->\n" +
                        "\t\t</div><!--// hd -->\n" +
                        "\n" +
                        "\t\t<div id=\"bd\">\n" +
                        "\t\t\t<div id=\"yui-main\">\n" +
                        "\t\t\t\t<div class=\"yui-b\">\n" +
                        "\n" +
                        "\t\t\t\t\t<div class=\"yui-gf\">\n" +
                        "\t\t\t\t\t\t<div class=\"yui-u first\">\n" +
                        "\t\t\t\t\t\t\t<h2>Objective</h2>\n" +
                        "\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t<div class=\"yui-u\">\n" +
                        "\t\t\t\t\t\t\t<p class=\"enlarge\">\n" +
                        "\t\t\t\t\t\t\t\t"+objective+" \n" +
                        "\t\t\t\t\t\t\t</p>\n" +
                        "\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t</div><!--// .yui-gf -->\n" +
                        "\n" +
                        "\t\t\t\t\t<div class=\"yui-gf\">\n" +
                        "\t\t\t\t\t\t<div class=\"yui-u first\">\n" +
                        "\t\t\t\t\t\t\t<h2>Skills</h2>\n" +
                        "\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t<div class=\"yui-u\">\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t<div class=\"talent\">\n" +
                        "\t\t\t\t\t\t\t\t\t<h2>"+skills+"</h2>\n" +

                        "\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t</div><!--// .yui-gf -->\n" +
                        "\n" +

                        "\n" +
                        "\t\t\t\t\t<div class=\"yui-gf\">\n" +
                        "\t\n" +
                        "\t\t\t\t\t\t<div class=\"yui-u first\">\n" +
                        "\t\t\t\t\t\t\t<h2>Experience</h2>\n" +
                        "\t\t\t\t\t\t</div><!--// .yui-u -->\n" +
                        "\n" +
                        "\t\t\t\t\t\t<div class=\"yui-u\">\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t<div class=\"job\">\n" +
                        "\t\t\t\t\t\t\t\t<h3>"+experience+"</h3>\n" +

                        "\t\t\t\t\t\t</div><!--// .yui-u -->\n" +
                        "\t\t\t\t\t</div><!--// .yui-gf -->\n" +
                        "\n" +

                        "\t\t\t\t\t<div class=\"yui-gf last\">\n" +
                        "\t\t\t\t\t\t<div class=\"yui-u first\">\n" +
                        "\t\t\t\t\t\t\t<h2>Education</h2>\n" +
                        "\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t<div class=\"yui-u\">\n" +
                        "\t\t\t\t\t\t\t<h2>"+college+"</h2>\n" +
                        "\t\t\t\t\t\t\t<h3>"+degreeName+" &mdash; <strong>"+cgpa+" CGPA</strong> </h3>\n" +
                        "\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t</div><!--// .yui-gf -->\n" +
                        "\n" +
                        "\n" +
                        "\t\t\t\t</div><!--// .yui-b -->\n" +
                        "\t\t\t</div><!--// yui-main -->\n" +
                        "\t\t</div><!--// bd -->\n" +
                        "\n" +
                        "\t\t<div id=\"ft\">\n" +
                        "\t\t\t<p>"+firstName+" "+lname+" &mdash; <a href=\"mailto:"+email+"\">"+email+"</a> &mdash; (313) - 867-5309</p>\n" +
                        "\t\t</div><!--// footer -->\n" +
                        "\n" +
                        "\t</div><!-- // inner -->\n" +
                        "\n" +
                        "\n" +
                        "</div><!--// doc -->\n" +
                        "\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n" +
                        "\n";
            }
            else {
                templateToShow="<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "<title>"+firstName+" "+lname+" - Curriculum Vitae</title>\n" +
        "\n" +
        "<meta name=\"viewport\" content=\"width=device-width\"/>\n" +
        "<meta name=\"description\" content=\"The Curriculum Vitae of "+firstName+" "+lname+".\"/>\n" +
        "<meta charset=\"UTF-8\"> \n" +
        "\n" +
        "<link type=\"text/css\" rel=\"stylesheet\" href=\"file:///android_asset/template3/style.css\">\n" +
        "<link href='http://fonts.googleapis.com/css?family=Rokkitt:400,700|Lato:400,300' rel='stylesheet' type='text/css'>\n" +
        "\n" +
        "<!--[if lt IE 9]>\n" +
        "<script src=\"file:///android_asset/template3///html5shiv.googlecode.com/svn/trunk/html5.js\"></script>\n" +
        "<![endif]-->\n" +
        "</head>\n" +
        "<body id=\"top\">\n" +
        "<div id=\"cv\" class=\"instaFade\">\n" +
        "\t<div class=\"mainDetails\">\n" +
        "\t\t<div id=\"headshot\" class=\"quickFade\">\n" +
        "\t\t\t<img src=\"file:///android_asset/template3/headshot.jpg\" alt=\"Alan Smith\" />\n" +
        "\t\t</div>\n" +
        "\t\t\n" +
        "\t\t<div id=\"name\">\n" +
        "\t\t\t<h1 class=\"quickFade delayTwo\">"+firstName+" "+lname+"</h1>\n" +
        "\t\t\t<h2 class=\"quickFade delayThree\">JOB TITLE DAL LENA</h2>\n" +
        "\t\t</div>\n" +
        "\t\t\n" +
        "\t\t<div id=\"contactDetails\" class=\"quickFade delayFour\">\n" +
        "\t\t\t<ul>\n" +
        "\t\t\t\t<li>e: <a href=\""+email+"\" target=\"_blank\">"+email+"</a></li>\n" +
        "\t\t\t\t<li>w: <a href=\""+website+"\">"+website+"</a></li>\n" +
        "\t\t\t\t<li>m: "+phoneNumber+"</li>\n" +
        "\t\t\t</ul>\n" +
        "\t\t</div>\n" +
        "\t\t<div class=\"clear\"></div>\n" +
        "\t</div>\n" +
        "\t\n" +
        "\t<div id=\"mainArea\" class=\"quickFade delayFive\">\n" +
        "\t\t<section>\n" +
        "\t\t\t<article>\n" +
        "\t\t\t\t<div class=\"sectionTitle\">\n" +
        "\t\t\t\t\t<h1>Objective</h1>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t\n" +
        "\t\t\t\t<div class=\"sectionContent\">\n" +
        "\t\t\t\t\t<p>"+objective+"</p>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t</article>\n" +
        "\t\t\t<div class=\"clear\"></div>\n" +
        "\t\t</section>\n" +
        "\t\t\n" +
        "\t\t\n" +
        "\t\t<section>\n" +
        "\t\t\t<div class=\"sectionTitle\">\n" +
        "\t\t\t\t<h1>Work Experience</h1>\n" +
        "\t\t\t</div>\n" +
        "\t\t\t\n" +
        "\t\t\t<div class=\"sectionContent\">\n" +
        "\t\t\t\t<article>\n" +
        "\t\t\t\t\t<h2>"+experience+"</h2>\n" +

        "\t\t\t\t</article>\n" +

        "\t\t\t<div class=\"clear\"></div>\n" +
        "\t\t</section>\n" +
        "\t\t\n" +
        "\t\t\n" +
        "\t\t<section>\n" +
        "\t\t\t<div class=\"sectionTitle\">\n" +
        "\t\t\t\t<h1>Key Skills</h1>\n" +
        "\t\t\t</div>\n" +
        "\t\t\t\n" +
        "\t\t\t<div class=\"sectionContent\">\n" +
        "\t\t\t\t<ul class=\"keySkills\">\n" +
        "\t\t\t\t\t<li>"+skills+"</li>\n" +

        "\t\t\t\t</ul>\n" +
        "\t\t\t</div>\n" +

        "\t\t\t<div class=\"clear\"></div>\n" +
        "\t\t</section>\n" +

                        "\t\t\n" +
                        "\t\t<section>\n" +
                        "\t\t\t<div class=\"sectionTitle\">\n" +
                        "\t\t\t\t<h1>Percentage/CGPA</h1>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t\n" +
                        "\t\t\t<div class=\"sectionContent\">\n" +
                        "\t\t\t\t<ul class=\"keySkills\">\n" +
                        "\t\t\t\t\t<li>"+cgpa+"</li>\n" +
                        "\t\t\t\t</ul>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div class=\"clear\"></div>\n" +
                        "\t\t</section>\n" +

        "\t\t\n" +
        "\t\t\n" +
        "\t\t<section>\n" +
        "\t\t\t<div class=\"sectionTitle\">\n" +
        "\t\t\t\t<h1>Education</h1>\n" +
        "\t\t\t</div>\n" +
        "\t\t\t\n" +
        "\t\t\t<div class=\"sectionContent\">\n" +
        "\t\t\t\t<article>\n" +
        "\t\t\t\t\t<h2>College/University</h2>\n" +
        "\t\t\t\t\t<p class=\"subDetails\">Qualification</p>\n" +
        "\t\t\t\t\t<p"+college+"</p>\n" +
        "\t\t\t\t</article>\n" +
        "\t\t\t\t\n" +
        "\t\t\t\t<article>\n" +
        "\t\t\t\t\t<h2>Degree</h2>\n" +
        "\t\t\t\t\t<p class=\"subDetails\">Qualification</p>\n" +
        "\t\t\t\t\t<p>"+degreeName+"</p>\n" +
        "\t\t\t\t</article>\n" +
        "\t\t\t</div>\n" +
        "\t\t\t<div class=\"clear\"></div>\n" +
        "\t\t</section>\n" +
        "\t\t\n" +
        "\t</div>\n" +
        "</div>\n" +
        "<script type=\"text/javascript\">\n" +
        "var gaJsHost = ((\"https:\" == document.location.protocol) ? \"https://ssl.\" : \"http://www.\");\n" +
        "document.write(unescape(\"%3Cscript src='\" + gaJsHost + \"google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E\"));\n" +
        "</script>\n" +
        "<script type=\"text/javascript\">\n" +
        "var pageTracker = _gat._getTracker(\"UA-3753241-1\");\n" +
        "pageTracker._initData();\n" +
        "pageTracker._trackPageview();\n" +
        "</script>\n" +
        "</body>\n" +
        "</html>";
            }
        }
        else
        {
            Toast.makeText(TemplatePlacer.this,"Some Error While fetching database",Toast.LENGTH_LONG).show();
        }
        webView = (WebView) findViewById(R.id.webView);
        // displaying text in WebView

        webView.loadDataWithBaseURL(null, templateToShow, "text/html", "utf-8", null);
    }
}

