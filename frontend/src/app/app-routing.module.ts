import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './componant/home/home.component';
import { TrackTableComponent } from './componant/tracks/track-table/track-table.component';
import { TrackEditComponent } from './componant/tracks/track-edit/track-edit.component';
import { TrackAddComponent } from './componant/tracks/track-add/track-add.component';
import { ArtistTableComponent } from './componant/artists/artist-table/artist-table.component';
import { ArtistEditComponent } from './componant/artists/artist-edit/artist-edit.component';
import { ArtistAddComponent } from './componant/artists/artist-add/artist-add.component';
import { ArtistViewComponent } from './componant/artists/artist-view/artist-view.component';

const routes: Routes = [
	{path:'', component:HomeComponent},
	{path:'tracks', component:TrackTableComponent},
	{path:'tracks/edit/:id', component:TrackEditComponent},
	{path:'tracks/add', component:TrackAddComponent},
	{path:'artists', component:ArtistTableComponent},
	{path:'artists/edit/:id', component:ArtistEditComponent},
	{path:'artists/add', component:ArtistAddComponent},
	{path:'artists/view/:id', component:ArtistViewComponent},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
