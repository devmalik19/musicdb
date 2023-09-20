import { Component, OnInit, ViewChild } from '@angular/core';
import { Artist } from '../../../data/model/artist';
import { MatTableDataSource} from "@angular/material/table";
import { MatPaginator} from "@angular/material/paginator";
import { ArtistService } from '../../../service/artist.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-artist-table',
  templateUrl: './artist-table.component.html',
  styleUrls: ['./artist-table.component.css']
})
export class ArtistTableComponent 
{
  constructor(private artistService:ArtistService, private router: Router ){};

	title = 'music-db';
	displayedColumns = ['name', 'biography', 'dob', 'type'];
	artists: Artist[] = [];
	dataSource = new MatTableDataSource<Artist>();

	ngOnInit()
	{
		this.artistService.getArtistList().subscribe(response=>{
			this.dataSource.data = response.data; 
			this.dataSource.paginator = this.paginator;
		});
	}

	applyFilter(event: Event) 
	{
		const serach = (event.target as HTMLInputElement).value;
		this.artistService.getArtistList(serach).subscribe(response=>{
			this.dataSource.data = response.data; 
		});
	}

	onRowClicked(row:any) 
	{
		console.log('Row clicked: ', row.id);
		this.router.navigate(['/artists/edit', row.id]);
	}
	
	add()
	{
		this.router.navigate(['/artists/add']);
	}

	@ViewChild('paginator') paginator: MatPaginator | any;

}
