import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GENRE } from 'src/app/data/constant/genre';
import { Artist } from 'src/app/data/model/artist';
import { Track } from 'src/app/data/model/track';
import { ArtistService } from 'src/app/service/artist.service';
import { CollaboratorService } from 'src/app/service/collaborator.service';
import { TrackService } from 'src/app/service/track.service';


@Component({
  selector: 'app-track-edit',
  templateUrl: './track-edit.component.html',
  styleUrls: ['./track-edit.component.css']
})

export class TrackEditComponent implements OnInit 
{
    constructor(private activatedRoute: ActivatedRoute,
                private artistService: ArtistService,
                private collabService: CollaboratorService,
                private router: Router, 
                private trackService: TrackService ){};
    
    genreList = Object.values(GENRE).filter(value => typeof value === 'string');

    track:Track = {} as Track            
    id:string = "";
    title:string = "";
    album:string = "";
    genre:string = "";
    length:string = "";
    release:string  = "";
    language:string  = "";   
    artist:Artist = {} as Artist;
    artists:Artist[] = [];          

    ngOnInit()
    {
      this.activatedRoute.params.subscribe(params => {
          this.id = params['id'];
          this.trackService.getTrackById(this.id).subscribe(response=>{
            this.title = response.data.title
            this.album = response.data.album
            this.genre = response.data.genre
            this.length = response.data.length
            this.release = response.data.release
            this.language = response.data.language
          });
      });

      this.doFilter("");
    }

    onSubmit(): void 
    {
      this.track.id = this.id
      this.track.title = this.title
      this.track.album = this.album
      this.track.genre = this.genre
      this.track.length = this.length
      this.track.release = this.release
      this.track.language = this.language

      this.trackService.update(this.track).subscribe(response=>{
        if(this.artist.id==undefined)
        {
          this.router.navigate(['/tracks']);
        }
        else 
        {
          this.collabService.add(this.track.id, this.artist.id).subscribe(response=>{
            this.router.navigate(['/tracks']);
          })
        }
      });
    }

    doFilter(search:string):void
    {
      this.artistService.getArtistList(search).subscribe(response=>{
        if(response.data!="")
          this.artists = response.data; 
      });
    }

    displayFn(artist: Artist): string 
    {
      return artist && artist.name ? artist.name : '';
    }
}
