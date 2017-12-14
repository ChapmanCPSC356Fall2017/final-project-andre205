package edu.chapman.cpsc356.routegenerator.models;

import org.joda.time.DateTime;

import java.util.UUID;

public class RouteModel
{
    public void setId(String id) {
        this.id = id;
    }

    private String id;

    private String title;

    private String startLocation;

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private String destinationLocation;

    private String notes;

    private DateTime date;

    public RouteModel()
    {
        this.id = UUID.randomUUID().toString();
        this.date = DateTime.now();
    }

    public RouteModel(String t, String s, String d, String n, String id, DateTime dt)
    {
        this.title = t;
        this.startLocation = s;
        this.destinationLocation = d;
        this.notes = n;
        if(id != "")
            this.id = id;
        else
            id = UUID.randomUUID().toString();
        this.date = dt;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public DateTime getDate()
    {
        return date;
    }

    public void setDate(DateTime date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ROUTE [title " + title + ", date " + date + "]";
    }
}
