import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TYPES } from 'src/app/data/constant/type';
import { Artist } from 'src/app/data/model/artist';
import { AliasService } from 'src/app/service/alias.service';
import { ArtistService } from 'src/app/service/artist.service';

@Component({
  selector: 'app-artist-edit',
  templateUrl: './artist-edit.component.html',
  styleUrls: ['./artist-edit.component.css']
})

export class ArtistEditComponent implements OnInit
{
  constructor(private activatedRoute: ActivatedRoute,
    private router: Router, 
    private aliasService: AliasService,
    private artistService: ArtistService ){};

   artistType = Object.values(TYPES).filter(value => typeof value === 'string');

   artist:Artist = {} as Artist
   id:string = "";
   name:string  = "";
   biography:string  = "";
   type:string  = "";
   dob:string  = "";
   alias:string=""

   ngOnInit()
   {
     this.activatedRoute.params.subscribe(params => {
         this.id = params['id'];
         this.artistService.getArtistById(this.id).subscribe(response=>{
           this.name = response.data.name
           this.biography = response.data.biography
           this.type = response.data.type
           this.dob = response.data.dob
           this.alias = response.data.aliases
         });
     });
   }

   onSubmit(): void 
   {
     this.artist.id = this.id
     this.artist.name = this.name
     this.artist.biography = this.biography
     this.artist.type = this.type
     this.artist.dob = this.dob

     delete this.artist.aliases

     this.artistService.update(this.artist).subscribe(response=>{
        this.aliasService.add(this.artist.id, this.alias).subscribe(response=>{
          this.router.navigate(['/artists']);
        })
     });
   }

}
