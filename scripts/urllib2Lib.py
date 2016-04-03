#!/usr/bin/env python

import urllib2
import httplib
import exceptions

def urllib2_testing():
	try:
		timeout = 30
		#proxy = 'http://192.168.56.1:8888'
		# url = "http://www.bsp.gov.ph/banking/quasi.pdf"
		# url = "http://cca.hawaii.gov/dfi/files/2013/05/Mortgage-Servicer-04.01.14.pdf"
		# url = "http://www.sec.gov.ph/investorinfo/registeredentity/GSEDs.pdf"
		# url = "http://www.sec.gov.ph/investorinfo/registeredentity/Registered%20BDD%20PSE%20Participants%20092008.pdf"
		url = "http://acpr.banque-france.fr/fileadmin/user_upload/acp/Agrements_et_autorisations/Procedures_secteur_assurance/Passeport_europeen_assurance/201312-Succursales-europeennes-entreprises-francaises-exercant-en-LPS-dans-EEE.pdf"
		# proxy = urllib2.ProxyHandler({'http': proxy})
		# opener = urllib2.build_opener(proxy)
		# urllib2.install_opener(opener)
		headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36', 'Accept' : '*/*'}
		request = urllib2.Request(url, headers=headers)
		body = urllib2.urlopen(request, timeout=timeout)
		html = body.read()
		print "Successfully connected to the website!"
		print "Successful Response Headers: %s" % body.info()
		print "Successful Response Status Code: %s" % body.code
	except (httplib.BadStatusLine, urllib2.HTTPError, exceptions.NameError, urllib2.URLError) as e:
		print "Error: %s" % e
		print "Could not connect to the website :("


	with open(filename, "wb") as f:
		f.write(html)

if __name__ == '__main__':
	urllib2_testing()
