package ru.javastudy.mvcHtml5Angular.model;


public class Question {
    private String typeQuestion;
    private String content;
    private String id;
    private String [] answers;

    public Question(String content,String type, String[] answers, String id){
        this.typeQuestion = type;
        this.content = content;
        this.answers = answers;
        this.id = id;
    }

    public void setTypeQuestion(String type){
        this.typeQuestion = type;
    }
    public void setId(String id){
        this.id = id;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setAnswers(String[] answers){
        this.answers = answers;
    }

    public String getContent(){return this.content; }
    public String getTypeQuestion(){return this.typeQuestion; }
    public String[] getAnswers(){return this.answers; }
    public String getId(){return this.id; }
}
