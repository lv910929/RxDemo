package com.lv.rxdemo.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lv.rxdemo.R;
import com.lv.rxdemo.databinding.ItemDesignBinding;
import com.lv.rxdemo.model.VRModel;
import com.lv.rxdemo.viewmodel.ItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lv on 2016/7/19.
 */
public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.ViewHolder> {

    private List<VRModel> vrModels;

    public DesignAdapter() {
        this.vrModels = Collections.emptyList();
    }

    public void setVrModels(List<VRModel> vrModels) {
        this.vrModels = vrModels;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDesignBinding itemDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_design, parent, false);
        return new ViewHolder(itemDesignBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindDesign(vrModels.get(position));
    }

    @Override
    public int getItemCount() {
        return vrModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemDesignBinding itemDesignBinding;

        public ViewHolder(ItemDesignBinding itemDesignBinding) {
            super(itemDesignBinding.cardviewItemDesign);
            this.itemDesignBinding = itemDesignBinding;
        }

        void bindDesign(VRModel vrModel) {
            if (itemDesignBinding.getItemViewModel()==null) {
                itemDesignBinding.setItemViewModel(new ItemViewModel(itemView.getContext(), vrModel));
            } else {
                itemDesignBinding.getItemViewModel().setVrModel(vrModel);
            }
        }
    }
}
