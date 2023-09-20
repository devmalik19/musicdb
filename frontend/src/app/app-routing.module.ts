import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TrackTableComponent } from './componant/tracks/track-table/track-table.component';
import { TrackEditComponent } from './componant/tracks/track-edit/track-edit.component';
import { TrackAddComponent } from './componant/tracks/track-add/track-add.component';

const routes: Routes = [
	{path:'tracks', component:TrackTableComponent},
	{path:'tracks/edit/:id', component:TrackEditComponent},
	{path:'tracks/add', component:TrackAddComponent}

];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
