/*
 * Copyright 2017 KG Soft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kgurgul.cpuinfo.features.information.android

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.kgurgul.cpuinfo.di.ViewModelInjectionFactory
import com.kgurgul.cpuinfo.features.information.base.BaseRvFragment
import com.kgurgul.cpuinfo.features.information.base.InfoItemsAdapter
import com.kgurgul.cpuinfo.utils.DividerItemDecoration
import com.kgurgul.cpuinfo.utils.lifecycleawarelist.ListLiveDataObserver
import javax.inject.Inject

/**
 * Fragment for android OS data
 *
 * @author kgurgul
 */
class AndroidInfoFragment : BaseRvFragment() {

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<AndroidInfoViewModel>

    private lateinit var viewModel: AndroidInfoViewModel
    private lateinit var infoItemsAdapter: InfoItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelInjectionFactory)
                .get(AndroidInfoViewModel::class.java)
    }

    override fun setupRecyclerViewAdapter() {
        infoItemsAdapter = InfoItemsAdapter(requireContext(), viewModel.listLiveData,
                InfoItemsAdapter.LayoutType.HORIZONTAL_LAYOUT)
        viewModel.listLiveData.listStatusChangeNotificator.observe(this,
                ListLiveDataObserver(infoItemsAdapter))
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext()))
        recyclerView.adapter = infoItemsAdapter
    }
}