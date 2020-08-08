//package com.tassioauad.moviecheck.view.fragment;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.cardview.widget.CardView;
//
//import com.squareup.picasso.Picasso;
//import com.tassioauad.moviecheck.R;
//import com.tassioauad.moviecheck.model.UrlConstants;
//import com.tassioauad.moviecheck.model.entity.Trailer;
//
//import java.util.ArrayList;
//
//public class MoviesTrailersRecycleAdapter extends RecyclerView.Adapter<MoviesTrailersRecycleAdapter.ViewHolder> {
//        Context mContext;
//        private ArrayList<Trailer> mTrailerMoviesArraylist;
//
//        public MoviesTrailersRecycleAdapter(ArrayList<Trailer> trailerMovies, Context context) {
//            mTrailerMoviesArraylist = trailerMovies;
//            mContext = context;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_tumbnail_layout, parent, false);
//            return new ViewHolder(v);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, final int position) {
//            if (mTrailerMoviesArraylist != null) {
//                Picasso.get().load(UrlConstants.TRAILER_THUMBNAIL_IMAGE_URL + mTrailerMoviesArraylist.get(position).getKey() + "/hqdefault.jpg").into(holder.trailerThumbnail);
//                holder.trailerThumbnailName.setText(mTrailerMoviesArraylist.get(position).getName());
//
//                holder.trailerCardView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        Intent i = new Intent(Intent.ACTION_VIEW);
//                        i.setData(Uri.parse(UrlConstants.YOUTUBE_WATCH_BASE_URL + mTrailerMoviesArraylist.get(position).getKey()));
//                        mContext.startActivity(i);
//                    }
//                });
//
//            }
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return mTrailerMoviesArraylist.size();
//        }
//
//        public static class ViewHolder extends RecyclerView.ViewHolder {
//            CardView trailerCardView;
//            ImageView trailerThumbnail;
//            TextView trailerThumbnailName;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//                trailerThumbnail = itemView.findViewById(R.id.trailerThumbnail);
//                trailerCardView = itemView.findViewById(R.id.trailerCardView);
//                trailerThumbnailName = itemView.findViewById(R.id.trailerThumbnailName);
//            }
//        }
//}
