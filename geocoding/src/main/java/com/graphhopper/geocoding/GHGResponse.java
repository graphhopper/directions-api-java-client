package com.graphhopper.geocoding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Karich
 */
public class GHGResponse
{

    private List<String> copyrights = new ArrayList<String>(5);
    private List<GHGEntry> hits;

    public GHGResponse()
    {
        this(5);
    }

    public GHGResponse( int no )
    {
        hits = new ArrayList<>(no);
    }

    public void setCopyrights( List<String> copyrights )
    {
        this.copyrights = copyrights;
    }

    public List<String> getCopyrights()
    {
        return copyrights;
    }

    public GHGResponse addCopyright( String cr )
    {
        copyrights.add(cr);
        return this;
    }

    public void setHits( List<GHGEntry> hits )
    {
        this.hits = hits;
    }

    public void add( GHGEntry entry )
    {
        hits.add(entry);
    }

    public List<GHGEntry> getHits()
    {
        return hits;
    }
}
