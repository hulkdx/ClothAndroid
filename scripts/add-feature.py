#!/usr/local/bin/python

# --------------------------------
# Imports
# --------------------------------

import sys
import os
from datetime import date
import re

def main():
    argv_len = len(sys.argv)
    if (argv_len == 2):
        arg1 = sys.argv[1]
        add_feature(arg1)
    else:
        show_usage()

def add_feature(name_of_feature):
    current_date = date.today().strftime("%d/%m/%Y")
    root_dir = '..'
    features_root_dir = root_dir + '/features-' + name_of_feature
    main_dir = features_root_dir + '/src/main'
    res_dir = main_dir + '/res/'
    src_dir = main_dir + '/java/hulkdx/com/features/' + name_of_feature
    make_dir(src_dir)
	# --------------------------------
    # settings.gradle
	# --------------------------------
    settings_gradle_path = root_dir + '/settings.gradle'
    feature_name_gradle = '\':features-' + name_of_feature + '\''
    with open(settings_gradle_path, 'r') as reader:
        read = reader.read()
        if not re.search(feature_name_gradle, read):
            seperator = ','
            split = read.split(seperator)
            split.insert(3, '\n        ' + feature_name_gradle)
            updated = seperator.join(split)
            with open(settings_gradle_path, 'w+') as writer:
                writer.write(updated)
	# --------------------------------
    # main/build.gradle
	# --------------------------------
    main_build_gradle_path = root_dir + '/main/build.gradle'
    with open(main_build_gradle_path, 'r') as reader:
        read = reader.read()
        if not re.search(feature_name_gradle, read):
            seperator = '\n'
            split = read.split(seperator)
            index = split.index("    implementation project(':features-common')")
            split.insert(index, '    implementation project(' + feature_name_gradle + ')')
            updated = seperator.join(split)
            with open(main_build_gradle_path, 'w+') as writer:
                writer.write(updated)
	# --------------------------------
	# root directory
	# --------------------------------

    make_file(features_root_dir + '/.gitignore', '''
/build
      ''')
    make_file(features_root_dir + '/build.gradle', '''apply plugin: 'com.android.library'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion compile_sdk_version
    defaultConfig {
        minSdkVersion min_sdk_version
        targetSdkVersion compile_sdk_version
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
        }
    }

}

dependencies {

    implementation project(':domain')
    implementation project(':features-common')

    kapt                daggerCompiler
    kapt                daggerAndroidCompiler
    implementation      dagger
    implementation      kotlinStd
    implementation      recyclerView
    implementation      constraintLayout
    implementation      appcompat
    implementation      liveData

}
      ''')

	# --------------------------------
	# DI directory
	# --------------------------------
    di_dir = src_dir + '/di'
    make_dir(di_dir)
    make_file(di_dir + '/' + name_of_feature.capitalize() + 'BindingModule.kt', '''package hulkdx.com.features.''' + name_of_feature + '''.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hulkdx.com.features.common.di.annotations.FragmentScoped
import hulkdx.com.features.''' + name_of_feature + '''.view.''' + name_of_feature.capitalize() + '''Fragment

/**
 * Created by Mohammad Jafarzadeh Rezvan on ''' + current_date + '''.
 */
@Module
abstract class ''' + name_of_feature.capitalize() + '''BindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        ''' + name_of_feature.capitalize() + '''ViewModelModule::class
    ])
    internal abstract fun ''' + name_of_feature + '''Fragment(): ''' + name_of_feature.capitalize() + '''Fragment

}
    ''')
    make_file(di_dir + '/' + name_of_feature.capitalize() + 'ViewModelModule.kt', '''package hulkdx.com.features.''' + name_of_feature + '''.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Binds
import dagger.multibindings.IntoMap
import hulkdx.com.features.common.di.CoreViewModelModule
import hulkdx.com.features.common.di.annotations.ViewModelKey
import hulkdx.com.features.''' + name_of_feature + '''.viewmodel.''' + name_of_feature.capitalize() + '''ViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on ''' + current_date + '''.
 */
@Module(includes = [
    CoreViewModelModule::class
])
internal abstract class ''' + name_of_feature.capitalize() + '''ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(''' + name_of_feature.capitalize() + '''ViewModel::class)
    abstract fun providesExploreViewModel(exploreViewModel: ''' + name_of_feature.capitalize() + '''ViewModel): ViewModel

}
    ''')
	# --------------------------------
	# View directory
	# --------------------------------
    view_dir = src_dir + '/view'
    make_dir(view_dir)
    make_file(view_dir + '/' + name_of_feature.capitalize() + 'Fragment.kt', '''package hulkdx.com.features.''' + name_of_feature + '''.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.features.common.util.observeFragment

import hulkdx.com.features.common.view.fragments.BaseFragment
import hulkdx.com.features.''' + name_of_feature + '''.R
import hulkdx.com.features.''' + name_of_feature + '''.viewmodel.''' + name_of_feature.capitalize() + '''ViewModel
import kotlinx.android.synthetic.main.fragment_''' + name_of_feature + '''.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on ''' + current_date + '''.
 */
class ''' + name_of_feature.capitalize() + '''Fragment : BaseFragment() {

    private lateinit var m''' + name_of_feature.capitalize() + '''ViewModel: ''' + name_of_feature.capitalize() + '''ViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        super.setupViewModel()
        m''' + name_of_feature.capitalize() + '''ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(''' + name_of_feature.capitalize() + '''ViewModel::class.java)
        // m''' + name_of_feature.capitalize() + '''ViewModel.TODOLiveData().observeFragment(this, Observer {
        //     when (it) {
        //     }
        // })
    }

    // region TODO Callback ------------------------------------------------------------------------

    // endregion TODO Callback ---------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun fragmentLayout(): Int {
        return R.layout.fragment_''' + name_of_feature + '''
    }

    // endregion Extra functions -------------------------------------------------------------------
}
    ''')
	# --------------------------------
	# ViewModel directory
	# --------------------------------
    view_model_dir = src_dir + '/viewmodel'
    make_dir(view_model_dir)
    make_file(view_model_dir + '/' + name_of_feature.capitalize() + 'ViewModel.kt', '''package hulkdx.com.features.''' + name_of_feature + '''.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on ''' + current_date + '''.
 */

class ''' + name_of_feature.capitalize() + '''ViewModel @Inject constructor(
    // private val mWhatNameUseCase: WhatNameUseCase
): ViewModel() {

    // private val mWhatNameLiveData = MutableLiveData<WhatNameUseCase.Result>()

    // region LiveData Setup -----------------------------------------------------------------------

    // fun WhatNameLiveData(): LiveData<WhatNameUseCase.Result> = mWhatNameLiveData

    // endregion LiveData Setup --------------------------------------------------------------------

    // fun somefunction() {
    //     mWhatNameUseCase.somefunction(
    //             callback = {
    //                 mWhatNameLiveData.value = it
    //             })
    // }

    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        // mWhatNameUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------
    // region Results ------------------------------------------------------------------------------
    // endregion Results ---------------------------------------------------------------------------

}
''')
	# --------------------------------
	# res/layout directory
	# --------------------------------
    res_layout_dir = res_dir + '/layout'
    make_dir(res_layout_dir)
    make_file(res_layout_dir + '/fragment_' + name_of_feature + '.xml', '''<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >

</androidx.constraintlayout.widget.ConstraintLayout>
    ''')
    # --------------------------------
	# res/values directory
	# --------------------------------
    res_values_dir = res_dir + '/values'
    make_dir(res_values_dir)
    make_file(res_values_dir + '/strings.xml', '''<resources>
</resources>
    ''')
    make_file(res_values_dir + '/styles.xml', '''<resources xmlns:android="http://schemas.android.com/apk/res/android">
</resources>
        ''')
    # --------------------------------
	# AndroidManifest
	# --------------------------------
    make_file(main_dir + '/AndroidManifest.xml', '''<?xml version="1.0" encoding="utf-8"?>
<manifest
    package="hulkdx.com.features.''' + name_of_feature + '''">
</manifest>
    ''')

# --------------------------------
# Extra
# --------------------------------

def make_file(file_path, content):
    f = open(file_path, 'w+')
    f.write(content)
    pass

def make_dir(dir_path):
    if not os.path.exists(dir_path):
        os.makedirs(dir_path)

def show_usage():
    print(
      "USAGE:\n\n" +
      "./add-feature.py name-of-feature\n"
      )

if __name__ == "__main__":
    main()
