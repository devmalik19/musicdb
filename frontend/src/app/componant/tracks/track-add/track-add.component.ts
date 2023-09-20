import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GENRE } from 'src/app/data/constant/genre';
import { Track } from 'src/app/data/model/track';
import { TrackService } from 'src/app/service/track.service';

@Component({
  selector: 'app-track-add',
  templateUrl: './track-add.component.html',
  styleUrls: ['./track-add.component.css'],
  providers: [DatePipe]
})

export class TrackAddComponent
{
   constructor(
                private router: Router, 
                private datePipe: DatePipe,
                private trackService: TrackService ){};
    
    genreList = Object.values(GENRE).filter(value => typeof value === 'string');

    title:string  = "";
    album:string  = "";
    genre:string  = "";
    length:string = "";
    release:Date  = new Date;
    language:string  = "";          

    onSubmit(): void 
    {
      let newTrack:Track = {} as Track            
      newTrack.title = this.title
      newTrack.album = this.album
      newTrack.genre = this.genre
      newTrack.length = this.length
      newTrack.release = this.datePipe.transform(this.release,'yyyy-MM-dd')
      newTrack.language = this.language

      console.log(newTrack)
      this.trackService.create(newTrack).subscribe(response=>{
        this.router.navigate(['/tracks']);
      });
    }

}
