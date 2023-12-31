import { Artist } from "./artist";

export interface Track 
{   
    id:string;
    title:string;
    album:string;
    genre:string;
    length:string;
    release:string | null;
    language:string;
    artistList:Artist[];
}
