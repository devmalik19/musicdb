import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideAnimations } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';


import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import {MatNativeDateModule} from '@angular/material/core';
import {MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatInputModule} from '@angular/material/input';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSortModule} from '@angular/material/sort';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list'; 
import {MatToolbarModule} from '@angular/material/toolbar'; 



import { HttpClientModule } from '@angular/common/http';
import { TrackTableComponent } from './componant/tracks/track-table/track-table.component';
import { TrackEditComponent } from './componant/tracks/track-edit/track-edit.component';
import { TrackAddComponent } from './componant/tracks/track-add/track-add.component';
import { ArtistTableComponent } from './componant/artists/artist-table/artist-table.component';
import { ArtistAddComponent } from './componant/artists/artist-add/artist-add.component';
import { ArtistEditComponent } from './componant/artists/artist-edit/artist-edit.component';
import {MatCardModule} from '@angular/material/card';
import { ArtistViewComponent } from './componant/artists/artist-view/artist-view.component';
import { HomeComponent } from './componant/home/home.component';
import { TableModule } from 'primeng/table';
import { PanelModule } from 'primeng/panel';




@NgModule({
	declarations: [AppComponent, TrackTableComponent, TrackEditComponent, TrackAddComponent, ArtistTableComponent, ArtistAddComponent, ArtistEditComponent, ArtistViewComponent, HomeComponent],
	providers: [
		DatePipe,
		{provide: MAT_DATE_LOCALE, useValue: 'en-GB'},
		provideAnimations()
	],
	bootstrap: [AppComponent],
	imports: [
		BrowserModule,
		AppRoutingModule,
		FormsModule,
		MatGridListModule,
		MatButtonModule,
		MatSelectModule,
		MatNativeDateModule,
		MatDatepickerModule,
		MatFormFieldModule, 
		MatInputModule,
        MatTableModule,
        MatSortModule,
        MatProgressSpinnerModule,
        MatPaginatorModule,
		HttpClientModule,
		MatListModule,
		MatSidenavModule,
		MatToolbarModule,
		MatCardModule,
		TableModule,
		PanelModule
    ]
})
export class AppModule {}
