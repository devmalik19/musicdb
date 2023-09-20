import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Track } from 'src/app/data/model/track';
import { ArtistService } from 'src/app/service/artist.service';
import { MatTableDataSource} from "@angular/material/table";
import { MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-artist-view',
  templateUrl: './artist-view.component.html',
  styleUrls: ['./artist-view.component.css']
})

export class ArtistViewComponent implements OnInit
{
  constructor(private activatedRoute: ActivatedRoute, private router: Router, private artistService: ArtistService ){}

   displayedColumns = ['title', 'album', 'genre', 'length', 'release', 'language']
   dataSource = new MatTableDataSource<Track>()

   id:string = ""
   name:string  = ""
   biography:string  = ""
   type:string  = ""
   dob:string  = ""


   ngOnInit()
   {
     this.activatedRoute.params.subscribe(params => {
         this.id = params['id'];
         this.artistService.getArtistById(this.id).subscribe(response=>{
           this.name = response.data.name
           this.biography = response.data.biography
           this.type = response.data.type
           this.dob = response.data.dob
         })

         this.artistService.getArtistTracks(this.id).subscribe(response=>{
              this.dataSource.data = response.data
              this.dataSource.paginator = this.paginator
          })

     })

   }
   
   @ViewChild('paginator') paginator: MatPaginator | any

}
