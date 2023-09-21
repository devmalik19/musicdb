import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GENRE } from 'src/app/data/constant/genre';
import { Artist } from 'src/app/data/model/artist';
import { Track } from 'src/app/data/model/track';
import { ArtistService } from 'src/app/service/artist.service';
import { CollaboratorService } from 'src/app/service/collaborator.service';
import { TrackService } from 'src/app/service/track.service';

@Component({
  selector: 'app-track-add',
  templateUrl: './track-add.component.html',
  styleUrls: ['./track-add.component.css'],
  providers: [DatePipe]
})

export class TrackAddComponent implements OnInit 
{
   constructor(
                private router: Router, 
                private datePipe: DatePipe,
                private artistService: ArtistService,
                private collabService : CollaboratorService,
                private trackService: TrackService ){};
    
    genreList = Object.values(GENRE).filter(value => typeof value === 'string');

    title:string  = "";
    album:string  = "";
    genre:string  = "";
    length:string = "";
    release:Date  = new Date;
    language:string  = "";   
    artist:Artist = {} as Artist;
    artists:Artist[] = [];       

    onSubmit(): void 
    {
      let newTrack:Track = {} as Track            
      newTrack.title = this.title
      newTrack.album = this.album
      newTrack.genre = this.genre
      newTrack.length = this.length
      newTrack.release = this.datePipe.transform(this.release,'yyyy-MM-dd')
      newTrack.language = this.language

      this.trackService.create(newTrack).subscribe(response=>{
        console.log(response.data)
        if(this.artist.id==undefined)
        {
          this.router.navigate(['/tracks']);
        }
        else 
        {
          this.collabService.add(response.data.id, this.artist.id).subscribe(response=>{
            this.router.navigate(['/tracks']);
          })
        }
      });
    }

    ngOnInit()
    {
      this.doFilter("");
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
