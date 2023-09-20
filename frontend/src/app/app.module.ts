import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideAnimations } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';

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



import { HttpClientModule } from '@angular/common/http';
import { TrackTableComponent } from './componant/tracks/track-table/track-table.component';
import { TrackEditComponent } from './componant/tracks/track-edit/track-edit.component';
import { TrackAddComponent } from './componant/tracks/track-add/track-add.component';

@NgModule({
	declarations: [AppComponent, TrackTableComponent, TrackEditComponent, TrackAddComponent],
	providers: [
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
		HttpClientModule
    ]
})
export class AppModule {}
