package com.iceservices.musicdb.helper;

import com.iceservices.musicdb.data.dao.Artist;
import com.iceservices.musicdb.data.dao.Track;
import com.iceservices.musicdb.data.dto.ArtistResponse;
import com.iceservices.musicdb.data.dto.TrackResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommonUtilities
{
    public LocalDate convertStringToLocalDate(String string)
    {
        return LocalDate.parse(string);
    }

    public List<TrackResponse> convertTrackListToTrackResponseList(List<Track> trackList)
    {
        List<TrackResponse> trackResponseList = new ArrayList<>();
        trackList.stream().forEach(track -> {
            trackResponseList.add(convertTrackToTrackResponseObject(track));
        });
        return  trackResponseList;
    }

    public TrackResponse convertTrackToTrackResponseObject(Track track)
    {
        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setId(track.getId());
        trackResponse.setTitle(track.getTitle());
        trackResponse.setAlbum(track.getAlbum());
        trackResponse.setLength(track.getLength());
        trackResponse.setLanguage(track.getLanguage());
        trackResponse.setRelease(track.getRelease().toString());
        trackResponse.setGenre(track.getGenre());
        trackResponse.setArtistList(this.getArtistList(track));
        return  trackResponse;
    }

    public List<ArtistResponse> getArtistList(Track track)
    {
        List<ArtistResponse> artistResponseList = new ArrayList<>();
        track.getCollaborator().stream().forEach(collaborator -> {
            ArtistResponse artistResponse = convertArtistToArtistResponseObject(collaborator.getArtist());
            artistResponse.setRole(String.valueOf(collaborator.getRole()));
            artistResponseList.add(artistResponse);
        });

        return artistResponseList;
    }

    public ArtistResponse convertArtistToArtistResponseObject(Artist artist)
    {
        ArtistResponse artistResponse = new ArtistResponse();
        artistResponse.setId(artist.getId());
        artistResponse.setName(artist.getName());
        artistResponse.setBiography(artist.getBiography());
        artistResponse.setDob(artist.getDob().toString());
        artistResponse.setType(String.valueOf(artist.getType()));
        return artistResponse;
    }

    public List<ArtistResponse> convertArtistToArtistResponseList(List<Artist> artistList)
    {
        List<ArtistResponse> artistResponseList = new ArrayList<>();
        artistList.forEach(artist -> {
            artistResponseList.add(convertArtistToArtistResponseObject(artist));
        });
        return artistResponseList;
    }
}
